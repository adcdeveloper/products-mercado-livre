package com.mercadolivre.presentation.products.detail.state

import com.mercadolivre.domain.entities.ProductDetail

sealed class ProductDetailState {

    data class ShowDetail(
        val thumbnail: String,
        val detail: ProductDetail
    ) : ProductDetailState()

    object ShowError : ProductDetailState()
}