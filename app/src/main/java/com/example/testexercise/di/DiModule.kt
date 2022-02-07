package com.example.testexercise.di

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.room.Room
import com.example.testexercise.data.retrofit.Author
import com.example.testexercise.data.retrofit.RetrofitRepository
import com.example.testexercise.data.retrofit.RepositoryImpl
import com.example.testexercise.data.retrofit.RestApi
import com.example.testexercise.data.room.converter.AuthorDataConverter
import com.example.testexercise.data.room.Database
import com.example.testexercise.data.room.converter.RepoConverter
import com.example.testexercise.data.retrofit.AuthorResponse
import com.example.testexercise.data.retrofit.Repo
import com.example.testexercise.data.retrofit.RepoResponse
import com.example.testexercise.data.retrofit.converter.AuthorResponseConverter
import com.example.testexercise.data.retrofit.converter.RepoResponseConverter
import com.example.testexercise.data.retrofit.converter.ResponseConverter
import com.example.testexercise.data.room.RoomRepository
import com.example.testexercise.data.room.RoomRepositoryImpl
import com.example.testexercise.domain.details.DetailsViewModel
import com.example.testexercise.domain.mainlist.MainListViewModel
import com.example.testexercise.utils.NavDestinations
import com.example.testexercise.utils.isOnline
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object DiModule {

    fun getDatabaseModule() = module {
        single { Room.databaseBuilder(androidContext(), Database::class.java, "database").build() }
        single { AuthorDataConverter() }
        single { RepoConverter() }

        single<RoomRepository> { RoomRepositoryImpl(db = get(), authorConverter = get(), repoConverter = get()) }
    }

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

        single<ResponseConverter<Author?, AuthorResponse>>(named("AUTHOR")) { AuthorResponseConverter() }
        single<ResponseConverter<Repo?, RepoResponse>>(named("REPO")) { RepoResponseConverter() }

        single<RetrofitRepository> { RepositoryImpl(restApi = get(), authorConverter = get(named("AUTHOR")), repoConverter = get(
            named("REPO"))) }
        single { NavHostController(androidContext()) }
        single { NavDestinations }
    }

    fun getViewModelModule() = module {
        viewModel { (navController: NavController) ->
            MainListViewModel(
                navController = navController,
                repository = get(),
                roomRepository = get(),
                navDestinations = get(),
                isOnlineOnStart = get()
            )
        }

        viewModel { (author: Author) ->
            DetailsViewModel(
                author = author,
                repository = get(),
                roomRepository = get(),
                isOnlineOnStart = get()
            )
        }
    }
}