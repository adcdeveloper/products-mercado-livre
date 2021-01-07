package com.mercadolivre.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.math.BigDecimal
import java.util.*

data class ProductsContent(
    val paging: Paging,
    val products: List<Product>
) {
    @Parcelize
    data class Product(
        val id: String,
        val siteId: String,
        val title: String,
        val seller: Seller,
        val price: BigDecimal,
        val currencyId: String,
        val availableQuantity: Int,
        val soldQuantity: Int,
        val buyingMode: String,
        val listingTypeId: String,
        val stopTime: Date,
        val condition: String,
        val permalink: String,
        val thumbnail: String,
        val acceptsMercadoPago: Boolean,
    ) : Parcelable

    data class Paging(
        val total: Int,
        val offset: Int,
        val limit: Int,
        val primaryResults: Int
    )

    @Parcelize
    data class Seller(
        val id: Int,
        val status: Int,
        val carDealer: Boolean,
        val realEstateAgency: Boolean,
        val tags: List<String>
    ) : Parcelable
}