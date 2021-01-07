package com.mercadolivre.di

import com.mercadolivre.data.repository.remote.RemoteRepository
import com.mercadolivre.domain.repositories.ProductsRepository
import com.mercadolivre.domain.usecase.SearchProductsUseCase
import com.mercadolivre.presentation.products.list.viewmodel.ProductsViewModel
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import org.mockito.Mockito.mock

class ProductsModuleTest : AutoCloseKoinTest() {

    @Before
    fun setup() {
        val appModule = module {
            single { mock(RemoteRepository::class.java) }
        }

        startKoin {
            modules(
                arrayListOf(appModule, productsModule)
            )
        }
    }

    @Test
    fun `should create instance of ProductsRepository when inject`() {
        val productsRepository by inject<ProductsRepository>()
        assertNotNull(productsRepository)
    }

    @Test
    fun `should create instance of SearchProductsUseCase when inject`() {
        val searchProductsUseCase by inject<SearchProductsUseCase>()
        assertNotNull(searchProductsUseCase)
    }

    @Test
    fun `should create instance of ProductsViewModel when inject`() {
        val productsViewModel by inject<ProductsViewModel>()
        assertNotNull(productsViewModel)
    }
}