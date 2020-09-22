package com.example.transactionapp.injection

import android.content.Context
import com.example.transactionapp.utils.NetworkConnection
import com.example.transactionapp.utils.TransactionValidation
import dagger.Module
import dagger.Provides

@Module
class UtilsModule {

    @Provides
    fun getNetworkConnection(context: Context): NetworkConnection =
        NetworkConnection(context)
    @Provides
    fun getTransactionValidation(): TransactionValidation =
        TransactionValidation()
}