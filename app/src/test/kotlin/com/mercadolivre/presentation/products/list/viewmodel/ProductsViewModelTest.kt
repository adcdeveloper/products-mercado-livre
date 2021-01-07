package com.mercadolivre.presentation.products.list.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mercadolivre.MainCoroutineRule
import com.mercadolivre.argumentCaptor
import com.mercadolivre.domain.entities.Failure
import com.mercadolivre.domain.entities.ProductsContent
import com.mercadolivre.core.result.Result
import com.mercadolivre.domain.usecase.SearchProductsUseCase
import com.mercadolivre.getProductsContent
import com.mercadolivre.getProductsContentEmpty
import com.mercadolivre.presentation.products.list.state.ProductsState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.core.IsInstanceOf
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.AutoCloseKoinTest
import org.mockito.Mockito.doAnswer
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class ProductsViewModelTest : AutoCloseKoinTest() {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var job: Job
    private lateinit var searchProductsUseCase: SearchProductsUseCase
    private lateinit var viewModel: ProductsViewModel

    @Before
    fun setup() {
        job = mock(Job::class.java)
        searchProductsUseCase = mock(SearchProductsUseCase::class.java)

        viewModel = ProductsViewModel(searchProductsUseCase)
    }

    @Test
    fun `should returns state ShowProducts when fetch query`() = runBlockingTest {
        val captorParams = argumentCaptor<SearchProductsUseCase.Params>()
        val captorScope = argumentCaptor<CoroutineScope>()
        val captorResult = argumentCaptor<Result<ProductsContent, Failure>.() -> Unit>()

        doAnswer {
            @Suppress("UNCHECKED_CAST")
            (it.arguments[2] as Result<ProductsContent, Failure>.() -> Unit)
                .invoke(Result.Success(getProductsContent()))
            job
        }.`when`(searchProductsUseCase).invoke(
            captorScope.capture(),
            captorParams.capture(),
            captorResult.captureLambda()
        )

        viewModel.loadProducts("esc")

        val expectedState = ProductsState.ShowProducts::class.java
        assertThat(viewModel.productsState.value, IsInstanceOf(expectedState))
    }

    @Test
    fun `should returns state ShowEmptyQuery when fetch query`() = runBlockingTest {
        val captorParams = argumentCaptor<SearchProductsUseCase.Params>()
        val captorScope = argumentCaptor<CoroutineScope>()
        val captorResult = argumentCaptor<Result<ProductsContent, Failure>.() -> Unit>()

        doAnswer {
            @Suppress("UNCHECKED_CAST")
            (it.arguments[2] as Result<ProductsContent, Failure>.() -> Unit)
                .invoke(Result.Success(getProductsContentEmpty()))
            job
        }.`when`(searchProductsUseCase).invoke(
            captorScope.capture(),
            captorParams.capture(),
            captorResult.captureLambda()
        )

        viewModel.loadProducts("ndaskj17823")

        val expectedState = ProductsState.ShowEmptyQuery::class.java
        assertThat(viewModel.productsState.value, IsInstanceOf(expectedState))
    }

    @Test
    fun `should returns state ShowError when fetch query`() = runBlockingTest {
        val captorParams = argumentCaptor<SearchProductsUseCase.Params>()
        val captorScope = argumentCaptor<CoroutineScope>()
        val captorResult = argumentCaptor<Result<ProductsContent, Failure>.() -> Unit>()

        doAnswer {
            @Suppress("UNCHECKED_CAST")
            (it.arguments[2] as Result<ProductsContent, Failure>.() -> Unit)
                .invoke(Result.Failure(Failure.ServerError))
            job
        }.`when`(searchProductsUseCase).invoke(
            captorScope.capture(),
            captorParams.capture(),
            captorResult.captureLambda()
        )

        viewModel.loadProducts("esc")

        val expectedState = ProductsState.ShowError::class.java
        assertThat(viewModel.productsState.value, IsInstanceOf(expectedState))
    }
}