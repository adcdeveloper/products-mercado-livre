package com.mercadolivre.domain.usecase

import com.mercadolivre.ID
import com.mercadolivre.core.result.flow
import com.mercadolivre.data.model.ProductsResponse
import com.mercadolivre.domain.entities.Failure
import com.mercadolivre.domain.entities.ProductsContent
import com.mercadolivre.domain.repositories.ProductsRepository
import com.mercadolivre.getProductsResponse
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import retrofit2.Response

class SearchProductsUseCaseTest {

    private lateinit var productsRepository: ProductsRepository
    private lateinit var searchProductsUseCase: SearchProductsUseCase

    @Before
    fun setup() {
        productsRepository = mock(ProductsRepository::class.java)

        searchProductsUseCase = SearchProductsUseCase(productsRepository)
    }

    @Test
    fun `should returns success with product detail when invoke use case`() = runBlockingTest {
        val response = Response.success(getProductsResponse(""))

        `when`(productsRepository.searchProducts(anyString())).thenReturn(response)

        var result: ProductsContent? = null

        val params = SearchProductsUseCase.Params(ID)
        searchProductsUseCase.invoke(this, params) {
            flow({
                result = it
            }, {

            })
        }

        assertNotNull(result)
    }

    @Test
    fun `should returns failure with error when invoke use case`() = runBlockingTest {
        val response = Response.error<ProductsResponse>(500, "".toResponseBody())

        `when`(productsRepository.searchProducts(anyString())).thenReturn(response)

        var result: Failure? = null

        val params = SearchProductsUseCase.Params(ID)
        searchProductsUseCase.invoke(this, params) {
            flow({

            }, {
                result = it
            })
        }

        assertNotNull(result)
    }

}