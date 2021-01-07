package com.mercadolivre.presentation.products.detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mercadolivre.domain.entities.ProductsContent
import com.mercadolivre.domain.usecase.ProductDetailUseCase
import com.mercadolivre.core.result.flow
import com.mercadolivre.presentation.products.detail.state.ProductDetailState
import kotlinx.coroutines.Job

class ProductDetailViewModel(
    private val productDetailUseCase: ProductDetailUseCase
): ViewModel() {

    private val _productDetailState by lazy { MutableLiveData<ProductDetailState>() }
    private val jobs: MutableList<Job> = mutableListOf()

    val productDetailState: LiveData<ProductDetailState>
        get() = _productDetailState

    override fun onCleared() {
        super.onCleared()
        jobs.forEach { it.cancel() }
    }

    fun getDetail(product: ProductsContent.Product) {
        val params = ProductDetailUseCase.Params(product.id)
        val job = productDetailUseCase.invoke(viewModelScope, params) {
            flow({
                val thumbnail = if (it.pictures.isNotEmpty()) {
                    it.pictures.first()
                } else {
                    product.thumbnail
                }

                _productDetailState.value = ProductDetailState.ShowDetail(thumbnail, it)
            }, {
                _productDetailState.value = ProductDetailState.ShowError
            })
        }
        jobs.add(job)
    }
}