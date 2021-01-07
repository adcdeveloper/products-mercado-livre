package com.mercadolivre.presentation.products.list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mercadolivre.domain.usecase.SearchProductsUseCase
import com.mercadolivre.core.result.flow
import com.mercadolivre.presentation.products.list.state.ProductsState
import kotlinx.coroutines.Job

class ProductsViewModel(
    private val searchProductsUseCase: SearchProductsUseCase
) : ViewModel() {

    private val _productsState by lazy { MutableLiveData<ProductsState>() }
    private val jobs: MutableList<Job> = mutableListOf()

    val productsState: LiveData<ProductsState>
        get() = _productsState

    override fun onCleared() {
        super.onCleared()
        jobs.forEach { it.cancel() }
    }

    fun loadProducts(query: String) {
        _productsState.value = ProductsState.ShowLoading

        val params = SearchProductsUseCase.Params(query)

        val job = searchProductsUseCase.invoke(viewModelScope, params) {
            flow({

                val state = if (it.products.isEmpty()) {
                    ProductsState.ShowEmptyQuery
                } else {
                    ProductsState.ShowProducts(it.products)
                }

                _productsState.postValue(state)

            }, {
                _productsState.postValue(ProductsState.ShowError)
            })
        }
        jobs.add(job)
    }
}