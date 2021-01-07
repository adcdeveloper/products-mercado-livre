package com.mercadolivre.domain.usecase

import com.mercadolivre.core.result.Result
import com.mercadolivre.domain.entities.Failure
import com.mercadolivre.domain.entities.ProductDetail
import com.mercadolivre.domain.mapper.toProductDetail
import com.mercadolivre.domain.repositories.ProductsRepository

class ProductDetailUseCase(
    private val repository: ProductsRepository
) : UseCase<ProductDetail, ProductDetailUseCase.Params>() {

    data class Params(val itemId: String)

    override suspend fun run(params: Params): Result<ProductDetail, Failure> {
        val response = repository.getDetail(params.itemId)

        if (response.isSuccessful && response.body() != null) {
            return Result.Success(response.body()!!.toProductDetail())
        }

        return Result.Failure(Failure.ServerError)
    }
}