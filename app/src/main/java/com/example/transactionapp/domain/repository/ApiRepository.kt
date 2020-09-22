package com.example.transactionapp.domain.repository

import com.example.transactionapp.client.RetrofitApiClientImplementation
import com.example.transactionapp.domain.model.TransactionDetails
import javax.inject.Inject

class ApiRepository
    @Inject constructor(private val api: RetrofitApiClientImplementation): Repository {

    override suspend fun getTransactionsResponse(): List<TransactionDetails> {
        return api.getTransactionsResponse()

    }

}
