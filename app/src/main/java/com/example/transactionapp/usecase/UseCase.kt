package com.example.transactionapp.usecase

import com.example.transactionapp.domain.model.TransactionDetails

interface UseCase {

    suspend fun request(): Any
}