package com.example.testexercise.di

import androidx.navigation.NavHostController
import com.example.testexercise.data.Repository
import com.example.testexercise.data.RepositoryImpl
import com.example.testexercise.data.RestApi
import com.example.testexercise.domain.MainListViewModel
import com.example.testexercise.domain.NavDestinations
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
//        single { Room.databaseBuilder(androidContext(), Database::class.java, "database").build() }

        single {
            get<Retrofit>().create(RestApi::class.java)
        }

        single<Retrofit> {
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
//                .create(RestApi::class.java)
        }

    }

    fun geRepositoryModule() = module {
        single<Repository> { RepositoryImpl(restApi = get()) }
        single { NavHostController(androidContext()) }
        single { NavDestinations }


        viewModel { MainListViewModel(repository = get(), navController = get(), navDestinations = get()) }
    }
}