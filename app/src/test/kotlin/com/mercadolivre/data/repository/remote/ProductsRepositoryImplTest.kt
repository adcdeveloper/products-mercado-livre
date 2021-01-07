package com.mercadolivre.data.repository.remote

import com.mercadolivre.ID
import com.mercadolivre.MainCoroutineRule
import com.mercadolivre.data.repository.remote.services.ProductsService
import com.mercadolivre.domain.repositories.ProductsRepository
import com.mercadolivre.getProductDetailResponse
import com.mercadolivre.getProductsResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import retrofit2.Response

@ExperimentalCoroutinesApi
internal class ProductsRepositoryImplTest {

    private lateinit var repository: ProductsRepository

    private lateinit var remoteRepository: RemoteRepository
    private lateinit var productsService: ProductsService

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        remoteRepository = mock(RemoteRepository::class.java)
        productsService = mock(ProductsService::class.java)

        repository = ProductsRepositoryImpl(remoteRepository)
    }

    @Test
    fun `should returns response when fetch search request`() = runBlockingTest {
        val query = "test"
        val response = Response.success(getProductsResponse(query))

        `when`(remoteRepository.getService(ProductsService::class.java)).thenReturn(productsService)
        `when`(productsService.search(query)).thenReturn(response)

        val result = repository.searchProducts(query)

        verify(productsService).search(query)

        assertEquals(result.body()?.siteId, response.body()?.siteId)
    }

    @Test
    fun `should returns response when fetch detail request`() = runBlockingTest {
        val itemId = ID
        val response = Response.success(getProductDetailResponse())

        `when`(remoteRepository.getService(ProductsService::class.java)).thenReturn(productsService)
        `when`(productsService.getDetail(itemId)).thenReturn(response)

        val result = repository.getDetail(itemId)

        verify(productsService).getDetail(itemId)

        assertEquals(result.body()?.siteId, response.body()?.siteId)
    }
}