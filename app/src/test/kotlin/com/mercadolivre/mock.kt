package com.mercadolivre

import com.mercadolivre.data.model.ProductDetailResponse
import com.mercadolivre.data.model.ProductsResponse
import com.mercadolivre.domain.entities.ProductsContent
import com.mercadolivre.domain.mapper.toProductDetail
import java.math.BigDecimal
import java.util.*

internal const val SITE_ID = "MLA"
internal const val ID = "${SITE_ID}321312"

internal fun getProductsResponse(query: String) = ProductsResponse(
    SITE_ID,
    query,
    getPagingResponse(),
    getProductsResponse()
)

internal fun getPagingResponse() = ProductsResponse.Paging(
    50,
    0,
    50,
    100
)

internal fun getProductsResponse() = mutableListOf(
    ProductsResponse.Product(
        "",
        SITE_ID,
        "",
        ProductsResponse.Seller(0, 0, true, true, mutableListOf()),
        BigDecimal(20),
        "",
        0,
        0,
        "",
        "",
        Date(),
        "",
        "",
        "",
        true
    )
)

internal fun getProductsContent() = ProductsContent(
    getPaging(),
    getProducts()
)

internal fun getProductsContentEmpty() = ProductsContent(
    getPaging(),
    mutableListOf()
)

internal fun getPaging() = ProductsContent.Paging(1,0, 1,1)

internal fun getProducts() = mutableListOf(
    ProductsContent.Product(
        "",
        SITE_ID,
        "",
        ProductsContent.Seller(0,0, true, true, mutableListOf()),
        BigDecimal(10),
        "pt-BR",
        1,
        1,
        "",
        "",
        Date(),
        "",
        "",
        "",
        true
    )
)

internal fun getProductDetailResponse() = ProductDetailResponse(
    ID,
    SITE_ID,
    "",
    "",
    "",
    "",
    "",
    BigDecimal(20),
    BigDecimal(20),
    BigDecimal(20),
    "",
    0,
    0,
    0,
    "",
    "",
    "",
    "",
    Date(),
    Date(),
    mutableListOf(ProductDetailResponse.PictureResponse(
        "",
        "",
        "",
        "",
        "",
        ""
    ))
)

internal fun getProductDetail() = getProductDetailResponse().toProductDetail()