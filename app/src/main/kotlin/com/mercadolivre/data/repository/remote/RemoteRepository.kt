package com.mercadolivre.data.repository.remote

interface RemoteRepository {

    fun <T> getService (service: Class<T>): T
}