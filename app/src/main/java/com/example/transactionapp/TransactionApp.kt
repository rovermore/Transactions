package com.example.transactionapp

import android.app.Application
import com.example.transactionapp.injection.*

class TransactionApp: Application() {

    companion object {
        lateinit var mDaggerAppComponent: AppComponent
        fun daggerAppComponent():AppComponent = mDaggerAppComponent
    }


    override fun onCreate() {
        super.onCreate()
        mDaggerAppComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .apiModule(ApiModule())
            .apiRepositoryModule(ApiRepositoryModule())
            .useCaseModule(UseCaseModule())
            .utilsModule(UtilsModule())
            .build()

    }
}