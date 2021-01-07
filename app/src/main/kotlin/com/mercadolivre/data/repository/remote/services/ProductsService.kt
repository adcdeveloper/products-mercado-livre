package com.mercadolivre.data.repository.remote.services

import com.mercadolivre.data.model.ProductDetailResponse
import com.mercadolivre.data.model.ProductsResponse
import com.mercadolivre.data.repository.remote.RemoteRepositoryImpl
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductsService {

    @GET("sites/${RemoteRepositoryImpl.SITE_ID}/search")
    suspend fun search(@Query("q") query: String): Response<ProductsResponse>

    @GET("items/{itemId}")
    suspend fun getDetail(@Path("itemId") itemId: String): Response<ProductDetailResponse>

}