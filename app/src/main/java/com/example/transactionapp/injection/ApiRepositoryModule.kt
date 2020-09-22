package com.example.transactionapp.injection

import com.example.transactionapp.client.RetrofitApiClientImplementation
import com.example.transactionapp.domain.repository.ApiRepository
import dagger.Module
import dagger.Provides

@Module
class ApiRepositoryModule {

    @Provides
    fun transactionApiRepository(retrofitApiClientImplementation: RetrofitApiClientImplementation): ApiRepository =
        ApiRepository(retrofitApiClientImplementation)
}