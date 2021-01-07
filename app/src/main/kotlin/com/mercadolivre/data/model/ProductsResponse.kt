package com.mercadolivre.data.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal
import java.util.*

data class ProductsResponse(
    @SerializedName("site_id")
    val siteId: String,

    @SerializedName("query")
    val query: String,

    @SerializedName("paging")
    val paging: Paging,

    @SerializedName("results")
    val results: List<Product>
) {
    data class Paging(
        @SerializedName("total")
        val total: Int,

        @SerializedName("offset")
        val offset: Int,

        @SerializedName("limit")
        val limit: Int,

        @SerializedName("primary_results")
        val primaryResults: Int
    )

    data class Product(
        @SerializedName("id")
        val id: String,

        @SerializedName("site_id")
        val siteId: String,

        @SerializedName("title")
        val title: String,

        @SerializedName("seller")
        val seller: Seller,

        @SerializedName("price")
        val price: BigDecimal,

        @SerializedName("currency_id")
        val currencyId: String,

        @SerializedName("available_quantity")
        val availableQuantity: Int,

        @SerializedName("sold_quantity")
        val soldQuantity: Int,

        @SerializedName("buying_mode")
        val buyingMode: String,

        @SerializedName("listing_type_id")
        val listingTypeId: String,

        @SerializedName("stop_time")
        val stopTime: Date,

        @SerializedName("condition")
        val condition: String,

        @SerializedName("permalink")
        val permalink: String,

        @SerializedName("thumbnail")
        val thumbnail: String,

        @SerializedName("accepts_mercadopago")
        val acceptsMercadoPago: Boolean,
    )

    data class Seller(
        @SerializedName("id")
        val id: Int,

        @SerializedName("power_seller_status")
        val status: Int,

        @SerializedName("car_dealer")
        val carDealer: Boolean,

        @SerializedName("real_estate_agency")
        val realEstateAgency: Boolean,

        @SerializedName("tags")
        val tags: List<String>
    )
}