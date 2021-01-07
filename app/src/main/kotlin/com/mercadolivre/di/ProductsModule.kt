package com.mercadolivre.di

import com.mercadolivre.data.repository.remote.ProductsRepositoryImpl
import com.mercadolivre.domain.repositories.ProductsRepository
import com.mercadolivre.domain.usecase.SearchProductsUseCase
import com.mercadolivre.presentation.products.list.viewmodel.ProductsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

internal val productsModule = module {
    factory<ProductsRepository> { ProductsRepositoryImpl(get()) }
    factory { SearchProductsUseCase(get()) }

    viewModel { ProductsViewModel(get()) }
}

private val loadProductsModule by lazy { loadKoinModules(productsModule) }

internal fun injectProductsModule() = loadProductsModule