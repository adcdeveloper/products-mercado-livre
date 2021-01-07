package com.mercadolivre.core.extensions

import java.math.BigDecimal
import java.text.NumberFormat

fun BigDecimal.toCurrency(): String {
    val format: NumberFormat = NumberFormat.getCurrencyInstance()
    return format.format(this)
}