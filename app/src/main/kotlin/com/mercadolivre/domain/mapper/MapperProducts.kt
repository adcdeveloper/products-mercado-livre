package com.mercadolivre.domain.mapper

import com.mercadolivre.data.model.ProductDetailResponse
import com.mercadolivre.data.model.ProductsResponse
import com.mercadolivre.domain.entities.ProductDetail
import com.mercadolivre.domain.entities.ProductsContent

fun ProductsResponse.toProductsContent(): ProductsContent {
    val products = mutableListOf<ProductsContent.Product>()

    results.forEach {
        val sellerContent: ProductsContent.Seller = ProductsContent.Seller(
            it.seller.id,
            it.seller.status,
            it.seller.carDealer,
            it.seller.realEstateAgency,
            it.seller.tags
        )

        val productContent: ProductsContent.Product = ProductsContent.Product(
            it.id,
            it.siteId,
            it.title,
            sellerContent,
            it.price,
            it.currencyId,
            it.availableQuantity,
            it.soldQuantity,
            it.buyingMode,
            it.listingTypeId,
            it.stopTime,
            it.condition,
            it.permalink,
            it.thumbnail,
            it.acceptsMercadoPago
        )

        products.add(productContent)
    }

    val pagingContent: ProductsContent.Paging = ProductsContent.Paging(
        paging.total,
        paging.offset,
        paging.limit,
        paging.primaryResults
    )

    return ProductsContent(pagingContent, products)
}

fun ProductDetailResponse.toProductDetail(): ProductDetail {
    val pictures = pictures.map { it.url }

    return ProductDetail(
        title,
        subtitle,
        categoryId,
        currencyId,
        availableQuantity,
        price,
        buyingMode,
        listingTypeId,
        condition,
        pictures
    )
}