package com.mercadolivre.domain.entities

import java.math.BigDecimal

data class ProductDetail(
    val title: String,
    val subtitle: String?,
    val categoryId: String,
    val currencyId: String,
    val availableQuantity: Int,
    val price: BigDecimal,
    val buyingMode: String?,
    val listingTypeId: String?,
    val condition: String?,
    val pictures: List<String>
)