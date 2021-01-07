package com.mercadolivre.domain.usecase

import com.mercadolivre.core.result.Result
import com.mercadolivre.domain.entities.Failure
import kotlinx.coroutines.*

abstract class UseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Result<Type, Failure>

    open operator fun invoke(
        scope: CoroutineScope,
        params: Params,
        onResult: (Result<Type, Failure>).() -> Unit = {}
    ): Job {
        val backgroundJob = scope.async { run(params) }
        return scope.launch { onResult(backgroundJob.await()) }
    }
}