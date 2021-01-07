package com.mercadolivre.domain.entities

sealed class Failure {
    object ServerError : Failure()
}