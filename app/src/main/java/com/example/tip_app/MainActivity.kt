package com.example.tip_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tip_app.databinding.ActivityMainBinding
import java.text.NumberFormat


class MainActivity : AppCompatActivity() {

     private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
       binding.calculateButton.setOnClickListener {calculateTip()}
    }

     private fun calculateTip() {
        val stringInTextField = binding.costOfService.text.toString()
         val cost = stringInTextField.toDoubleOrNull()
         if (cost == null) {
             binding.tipAmount.text = ""
             return
         }
         val tipPercentage = when(binding.tipOptions.checkedRadioButtonId){
             R.id.amazing_service_radioButton -> 0.20
             R.id.good_service_radioButton -> 0.18
             else ->0.15
         }
         var tip: Double = tipPercentage * cost

         val roundUp: Boolean = binding.RoundTipSwitch.isChecked
         if(roundUp) tip = kotlin.math.ceil(tip)

         val formattedTip =  NumberFormat.getCurrencyInstance().format(tip)
         binding.tipAmount.text = getString(R.string.tip_amount , formattedTip)
    }

}