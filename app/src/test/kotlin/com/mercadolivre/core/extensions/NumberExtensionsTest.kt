package com.mercadolivre.core.extensions

import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal

class NumberExtensionsTest {

    @Test
    fun `should format to currency when performed toCurrency`() {
        val value = BigDecimal(20.2)

        val result = value.toCurrency()
        val expected = "$20.20"

        assertEquals(expected, result)
    }
}