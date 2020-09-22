package com.example.transactionapp.client

import com.example.transactionapp.domain.model.TransactionDetails
import javax.inject.Inject

class RetrofitApiClientImplementation
    @Inject constructor(private val retrofitApiClient: RetrofitApiClient):
    RetrofitApiClient {

    override suspend fun getTransactionsResponse(): List<TransactionDetails> {
        return retrofitApiClient.getTransactionsResponse()
    }
}