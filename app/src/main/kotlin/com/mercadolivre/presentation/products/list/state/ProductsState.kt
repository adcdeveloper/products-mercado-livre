package com.mercadolivre.presentation.products.list.state

import com.mercadolivre.domain.entities.ProductsContent

sealed class ProductsState {

    data class ShowProducts(val products: List<ProductsContent.Product>) : ProductsState()

    object ShowLoading : ProductsState()

    object ShowEmptyQuery : ProductsState()

    object ShowError : ProductsState()
}