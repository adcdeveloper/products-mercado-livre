package com.mercadolivre.di

import com.mercadolivre.domain.usecase.ProductDetailUseCase
import com.mercadolivre.presentation.products.detail.viewmodel.ProductDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

internal val productDetailModule = module {
    factory { ProductDetailUseCase(get()) }

    viewModel { ProductDetailViewModel(get()) }
}

private val loadProductDetailModule by lazy { loadKoinModules(productDetailModule) }

internal fun injectProductDetailModule() = loadProductDetailModule