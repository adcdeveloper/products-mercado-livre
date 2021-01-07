package com.mercadolivre.data.repository.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal class RemoteRepositoryImpl : RemoteRepository {

    companion object {
        const val SITE_ID = "MLA"
        private const val BASE_URL = "https://api.mercadolibre.com/"
    }

    override fun <T> getService(service: Class<T>): T {
        val interceptor: Interceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(service)
    }
}