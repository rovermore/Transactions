package com.example.transactionapp.injection

import com.example.transactionapp.domain.repository.ApiRepository
import com.example.transactionapp.usecase.TransactionsUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun getTransactionsUseCase(repository: ApiRepository): TransactionsUseCase =
        TransactionsUseCase(repository)
}