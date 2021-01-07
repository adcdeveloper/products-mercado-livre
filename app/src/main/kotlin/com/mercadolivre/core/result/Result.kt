package com.mercadolivre.core.result

sealed class Result<out S, out F> {
    data class Success<out S>(val value: S) : Result<S, Nothing>()
    data class Failure<out F>(val value: F) : Result<Nothing, F>()
}

inline fun <S, F, T> Result<S, F>.flow(
    success: (S) -> T,
    failure: (F) -> T
): T = when (this) {
    is Result.Success -> success(value)
    is Result.Failure -> failure(value)
}