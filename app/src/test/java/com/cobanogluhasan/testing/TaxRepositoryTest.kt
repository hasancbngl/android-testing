package com.cobanogluhasan.testing

import com.cobanogluhasan.testing.repository.TaxRepository
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import kotlin.math.roundToInt

class TaxRepositoryTest {

    var repository: TaxRepository? = null

    @Before
    fun setup() {
        repository = TaxRepository()
    }

    @After
    fun tearDown() {
        repository = null
    }

    @Test
    fun `calculate income test`() {
        val netIncome = repository?.calculateNetIncome(1588.9, 0.18)
        //junit assertion
        // assertEquals(netIncome, 1302.8980000000001)
        //truth assertion
        assertThat(netIncome?.roundToInt()).isEqualTo(1303)
    }

    @Test
    fun `calculate net tax`() {
        val tax = repository?.calculateTax(1588.9, 0.18)
        //  assertEquals(tax, 286.002)
        assertThat(tax?.roundToInt()).isEqualTo(286)
    }
}