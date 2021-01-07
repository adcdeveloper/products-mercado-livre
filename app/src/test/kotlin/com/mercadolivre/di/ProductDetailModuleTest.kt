package com.mercadolivre.di

import com.mercadolivre.data.repository.remote.RemoteRepository
import com.mercadolivre.domain.repositories.ProductsRepository
import com.mercadolivre.domain.usecase.ProductDetailUseCase
import com.mercadolivre.presentation.products.detail.viewmodel.ProductDetailViewModel
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import org.mockito.Mockito.mock

class ProductDetailModuleTest : AutoCloseKoinTest() {

    @Before
    fun setup() {
        val appModule = module {
            single { mock(RemoteRepository::class.java) }
            single { mock(ProductsRepository::class.java) }
        }

        startKoin {
            modules(
                arrayListOf(appModule, productDetailModule)
            )
        }
    }

    @Test
    fun `should create instance of ProductDetailUseCase when inject`() {
        val productDetailUseCase by inject<ProductDetailUseCase>()
        assertNotNull(productDetailUseCase)
    }

    @Test
    fun `should create instance of ProductDetailViewModel when inject`() {
        val productDetailViewModel by inject<ProductDetailViewModel>()
        assertNotNull(productDetailViewModel)
    }
}