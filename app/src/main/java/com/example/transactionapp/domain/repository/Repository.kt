package com.example.transactionapp.domain.repository

import com.example.transactionapp.domain.model.TransactionDetails

interface Repository {

    suspend fun getTransactionsResponse(): List<TransactionDetails>
}