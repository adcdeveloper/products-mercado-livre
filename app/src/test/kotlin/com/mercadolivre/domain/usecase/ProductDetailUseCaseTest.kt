package com.mercadolivre.domain.usecase

import com.mercadolivre.ID
import com.mercadolivre.core.result.flow
import com.mercadolivre.data.model.ProductDetailResponse
import com.mercadolivre.domain.entities.Failure
import com.mercadolivre.domain.entities.ProductDetail
import com.mercadolivre.domain.repositories.ProductsRepository
import com.mercadolivre.getProductDetailResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import retrofit2.Response

@ExperimentalCoroutinesApi
class ProductDetailUseCaseTest {

    private lateinit var productsRepository: ProductsRepository
    private lateinit var productDetailUseCase: ProductDetailUseCase

    @Before
    fun setup() {
        productsRepository = mock(ProductsRepository::class.java)

        productDetailUseCase = ProductDetailUseCase(productsRepository)
    }

    @Test
    fun `should returns success with product detail when invoke use case`() = runBlockingTest {
        val response = Response.success(getProductDetailResponse())

        `when`(productsRepository.getDetail(anyString())).thenReturn(response)

        var result: ProductDetail? = null

        val params = ProductDetailUseCase.Params(ID)
        productDetailUseCase.invoke(this, params) {
            flow({
                result = it
            }, {

            })
        }

        assertNotNull(result)
    }

    @Test
    fun `should returns failure with error when invoke use case`() = runBlockingTest {
        val response = Response.error<ProductDetailResponse>(500, "".toResponseBody())

        `when`(productsRepository.getDetail(anyString())).thenReturn(response)

        var result: Failure? = null

        val params = ProductDetailUseCase.Params(ID)
        productDetailUseCase.invoke(this, params) {
            flow({

            }, {
                result = it
            })
        }

        assertNotNull(result)
    }
}