package com.mercadolivre.domain.usecase

import com.mercadolivre.core.result.Result
import com.mercadolivre.domain.entities.Failure
import com.mercadolivre.domain.entities.ProductsContent
import com.mercadolivre.domain.mapper.toProductsContent
import com.mercadolivre.domain.repositories.ProductsRepository

class SearchProductsUseCase(private val repository: ProductsRepository) :
    UseCase<ProductsContent, SearchProductsUseCase.Params>() {

    data class Params(val query: String)

    override suspend fun run(params: Params): Result<ProductsContent, Failure> {
        val response = repository.searchProducts(params.query)

        if (response.isSuccessful) {
            response.body()?.let {
                return Result.Success(it.toProductsContent())
            }
        }

        return Result.Failure(Failure.ServerError)
    }
}