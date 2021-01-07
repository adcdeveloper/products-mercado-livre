package com.mercadolivre.data.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal
import java.util.*

data class ProductDetailResponse(

    @SerializedName("id")
    val id: String,

    @SerializedName("site_id")
    val siteId: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("subtitle")
    val subtitle: String?,

    @SerializedName("seller_id")
    val sellerId: String,

    @SerializedName("category_id")
    val categoryId: String,

    @SerializedName("official_store_id")
    val officialStoreId: String,

    @SerializedName("price")
    val price: BigDecimal,

    @SerializedName("base_price")
    val basePrice: BigDecimal?,

    @SerializedName("original_price")
    val originalPrice: BigDecimal?,

    @SerializedName("currency_id")
    val currencyId: String,

    @SerializedName("initial_quantity")
    val initialQuantity: Int,

    @SerializedName("available_quantity")
    val availableQuantity: Int,

    @SerializedName("sold_quantity")
    val soldQuantity: Int,

    @SerializedName("buying_mode")
    val buyingMode: String?,

    @SerializedName("listing_type_id")
    val listingTypeId: String?,

    @SerializedName("condition")
    val condition: String?,

    @SerializedName("thumbnail")
    val thumbnail: String,

    @SerializedName("start_time")
    val startTime: Date,

    @SerializedName("stop_time")
    val stopTime: Date,

    @SerializedName("pictures")
    val pictures: List<PictureResponse>

) {

    data class PictureResponse(
        @SerializedName("id")
        val id: String,

        @SerializedName("url")
        val url: String,

        @SerializedName("secure_url")
        val secureUrl: String,

        @SerializedName("size")
        val size: String,

        @SerializedName("max_size")
        val maxSize: String,

        @SerializedName("quality")
        val quality: String
    )

}