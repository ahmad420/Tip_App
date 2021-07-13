package com.example.tip_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tip_app.databinding.ActivityMainBinding
import java.text.NumberFormat


class MainActivity : AppCompatActivity() {

     lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
       binding.calculateButton.setOnClickListener {calculateTip()}
    }

     fun calculateTip() {
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDouble()
         val selectedId =binding.tipOptions.checkedRadioButtonId
         val tipPercentage = when(selectedId){
             R.id.amazing_service_radioButton -> 0.20
             R.id.good_service_radioButton -> 0.18
             else ->0.15
         }
         var tip = tipPercentage * cost

         val roundUp = binding.RoundTipSwitch.isChecked
         if(roundUp){
             tip = kotlin.math.ceil(tip)

         }

         val formattedTip =  NumberFormat.getCurrencyInstance().format(tip)
         binding.tipAmount.text = getString(R.string.tip_amount , formattedTip)
    }

}