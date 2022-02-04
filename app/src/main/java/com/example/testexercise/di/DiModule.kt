package com.example.testexercise.di

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.testexercise.data.Author
import com.example.testexercise.data.Repository
import com.example.testexercise.data.RepositoryImpl
import com.example.testexercise.data.RestApi
import com.example.testexercise.domain.DetailsViewModel
import com.example.testexercise.domain.MainListViewModel
import com.example.testexercise.domain.NavDestinations
import com.example.testexercise.domain.isOnline
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object DiModule {
    fun getRetrofitModule() = module {

        single<RestApi> {
            Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create(
                        GsonBuilder()
                            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                            .excludeFieldsWithoutExposeAnnotation()
                            .create()
                    )
                )
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                        .build()
                )
                .build()
                .create(RestApi::class.java)
        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun getInternetConnectionModule() = module {
        factory { isOnline(androidContext()) }
    }

    fun getRepositoryModule() = module {
        single<Repository> { RepositoryImpl(restApi = get()) }
        single { NavHostController(androidContext()) }
        single { NavDestinations }
    }

    fun getViewModelModule() = module {
        viewModel { (navController: NavController) ->
            MainListViewModel(
                navController = navController,
                repository = get(),
                navDestinations = get()
            )
        }

        viewModel { (author: Author) ->
            DetailsViewModel(
                author = author,
                repository = get()
            )
        }
    }
}