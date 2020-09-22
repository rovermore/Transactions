package com.example.transactionapp.client

import com.example.transactionapp.domain.model.TransactionDetails
import retrofit2.http.GET

interface RetrofitApiClient {

    @GET("/transactions.json")
    suspend fun getTransactionsResponse()
            : List<TransactionDetails>
}