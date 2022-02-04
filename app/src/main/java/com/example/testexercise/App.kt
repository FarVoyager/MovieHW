package com.example.testexercise

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.testexercise.di.DiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                DiModule.getRetrofitModule(),
                DiModule.getRepositoryModule(),
                DiModule.getViewModelModule(),
                DiModule.getInternetConnectionModule()
            )
        }
    }
}