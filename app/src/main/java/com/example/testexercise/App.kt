package com.example.testexercise

import android.app.Application
import com.example.testexercise.di.DiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(DiModule.getRetrofitModule(), DiModule.geRepositoryModule())
        }
    }
}