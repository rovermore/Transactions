package com.example.transactionapp.usecase

import com.example.transactionapp.domain.model.TransactionDetails
import com.example.transactionapp.domain.repository.ApiRepository
import javax.inject.Inject

class TransactionsUseCase
    @Inject constructor(private val repository: ApiRepository): UseCase {

    override suspend fun request(): List<TransactionDetails>{
        return repository.getTransactionsResponse()
    }
}