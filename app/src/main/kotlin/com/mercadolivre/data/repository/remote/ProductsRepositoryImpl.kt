package com.mercadolivre.data.repository.remote

import com.mercadolivre.data.model.ProductDetailResponse
import com.mercadolivre.data.model.ProductsResponse
import com.mercadolivre.data.repository.remote.services.ProductsService
import com.mercadolivre.domain.repositories.ProductsRepository
import retrofit2.Response

class ProductsRepositoryImpl(
    private val remote: RemoteRepository
) : ProductsRepository {

    override suspend fun searchProducts(query: String): Response<ProductsResponse> {
        return remote
            .getService(ProductsService::class.java)
            .search(query)
    }

    override suspend fun getDetail(itemId: String): Response<ProductDetailResponse> {
        return remote
            .getService(ProductsService::class.java)
            .getDetail(itemId)
    }

}