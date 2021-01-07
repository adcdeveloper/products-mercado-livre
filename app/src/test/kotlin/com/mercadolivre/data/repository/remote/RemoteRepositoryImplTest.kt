package com.mercadolivre.data.repository.remote

import com.mercadolivre.data.repository.remote.services.ProductsService
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class RemoteRepositoryImplTest {

    private lateinit var remoteRepository: RemoteRepository

    @Before
    fun setup() {
        remoteRepository = RemoteRepositoryImpl()
    }

    @Test
    fun `should returns instance of service when getService is called`() {
        val service = remoteRepository.getService(ProductsService::class.java)

        assertNotNull(service)
    }
}