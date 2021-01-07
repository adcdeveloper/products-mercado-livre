package com.mercadolivre.presentation.products.detail.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mercadolivre.MainCoroutineRule
import com.mercadolivre.argumentCaptor
import com.mercadolivre.domain.entities.Failure
import com.mercadolivre.domain.entities.ProductDetail
import com.mercadolivre.domain.usecase.ProductDetailUseCase
import com.mercadolivre.core.result.Result
import com.mercadolivre.getProductDetail
import com.mercadolivre.getProducts
import com.mercadolivre.presentation.products.detail.state.ProductDetailState
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
class ProductDetailViewModelTest : AutoCloseKoinTest() {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var job: Job
    private lateinit var productDetailUseCase: ProductDetailUseCase
    private lateinit var viewModel: ProductDetailViewModel

    @Before
    fun setup() {
        job = mock(Job::class.java)
        productDetailUseCase = mock(ProductDetailUseCase::class.java)

        viewModel = ProductDetailViewModel(productDetailUseCase)
    }

    @Test
    fun `should returns state ShowDetail when fetch detail`() = runBlockingTest {
        val captorParams = argumentCaptor<ProductDetailUseCase.Params>()
        val captorScope = argumentCaptor<CoroutineScope>()
        val captorResult = argumentCaptor<Result<ProductDetail, Failure>.() -> Unit>()

        doAnswer {
            @Suppress("UNCHECKED_CAST")
            (it.arguments[2] as Result<ProductDetail, Failure>.() -> Unit)
                .invoke(Result.Success(getProductDetail()))
            job
        }.`when`(productDetailUseCase).invoke(
            captorScope.capture(),
            captorParams.capture(),
            captorResult.captureLambda()
        )

        viewModel.getDetail(getProducts().first())

        val expectedState = ProductDetailState.ShowDetail::class.java
        assertThat(viewModel.productDetailState.value, IsInstanceOf(expectedState))
    }

    @Test
    fun `should returns state ShowError when fetch detail`() = runBlockingTest {
        val captorParams = argumentCaptor<ProductDetailUseCase.Params>()
        val captorScope = argumentCaptor<CoroutineScope>()
        val captorResult = argumentCaptor<Result<ProductDetail, Failure>.() -> Unit>()

        doAnswer {
            @Suppress("UNCHECKED_CAST")
            (it.arguments[2] as Result<ProductDetail, Failure>.() -> Unit)
                .invoke(Result.Failure(Failure.ServerError))
            job
        }.`when`(productDetailUseCase).invoke(
            captorScope.capture(),
            captorParams.capture(),
            captorResult.captureLambda()
        )

        viewModel.getDetail(getProducts().first())

        val expectedState = ProductDetailState.ShowError::class.java
        assertThat(viewModel.productDetailState.value, IsInstanceOf(expectedState))
    }
}