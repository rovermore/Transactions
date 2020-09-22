package com.example.transactionapp.injection

import android.content.Context
import com.example.transactionapp.TransactionApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: TransactionApp) {

    @Provides
    @Singleton
    fun context(): Context = app.applicationContext

}