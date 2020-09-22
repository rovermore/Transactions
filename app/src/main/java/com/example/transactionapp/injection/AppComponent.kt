package com.example.transactionapp.injection

import com.example.transactionapp.screen.MainFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ApiModule::class,
        ApiRepositoryModule::class,
        UtilsModule::class,
        UseCaseModule::class
    ]
)
interface AppComponent {
    fun inject(mainFragment: MainFragment)
}