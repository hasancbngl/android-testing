package com.cobanogluhasan.testing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.cobanogluhasan.testing.repository.TaxRepository

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = TaxRepository()
        val netIncome = repository.calculateNetIncome(100.0, 0.1)
        val netTax = repository.calculateTax(100.0, 0.1)
        Log.i(TAG, "onCreate: income is: $netIncome , netTax: $netTax")
    }
}