package com.cobanogluhasan.testing.repository

class TaxRepository {

    fun calculateTax(grossIncome: Double, taxRate: Double): Double = grossIncome * taxRate

    fun calculateNetIncome(grossIncome: Double, taxRate: Double): Double =
        grossIncome - grossIncome * taxRate
}