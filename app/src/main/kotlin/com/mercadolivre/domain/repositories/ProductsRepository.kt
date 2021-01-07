package com.mercadolivre.domain.repositories

import com.mercadolivre.data.model.ProductDetailResponse
import com.mercadolivre.data.model.ProductsResponse
import retrofit2.Response

interface ProductsRepository {

    suspend fun searchProducts(query: String): Response<ProductsResponse>

    suspend fun getDetail(itemId: String): Response<ProductDetailResponse>

}