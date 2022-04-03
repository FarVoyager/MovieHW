package com.example.moviehw.di

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.room.Room
import com.example.moviehw.data.retrofit.ApiInterceptor
import com.example.moviehw.data.retrofit.Author
import com.example.moviehw.data.retrofit.RetrofitRepository
import com.example.moviehw.data.retrofit.RetrofitRepositoryImpl
import com.example.moviehw.data.retrofit.RestApi
import com.example.moviehw.data.room.converter.AuthorDataConverter
import com.example.moviehw.data.room.Database
import com.example.moviehw.data.room.converter.RepoConverter
import com.example.moviehw.data.retrofit.AuthorResponse
import com.example.moviehw.data.retrofit.Repo
import com.example.moviehw.data.retrofit.RepoResponse
import com.example.moviehw.data.retrofit.converter.AuthorResponseConverter
import com.example.moviehw.data.retrofit.converter.RepoResponseConverter
import com.example.moviehw.data.retrofit.converter.ResponseConverter
import com.example.moviehw.data.room.RoomRepository
import com.example.moviehw.data.room.RoomRepositoryImpl
import com.example.moviehw.domain.details.DetailsViewModel
import com.example.moviehw.domain.mainlist.MainListViewModel
import com.example.moviehw.utils.NavDestinations
import com.example.moviehw.utils.isOnline
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

        single<RoomRepository> {
            RoomRepositoryImpl(
                db = get(),
                authorConverter = get(),
                repoConverter = get()
            )
        }
    }

    fun getRetrofitModule() = module {
        single<RestApi> {
            Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org")
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
                        .addInterceptor(ApiInterceptor)
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

        single<ResponseConverter<Author?, AuthorResponse>>(named(RESPONSE_CONVERTER_AUTHOR)) { AuthorResponseConverter() }
        single<ResponseConverter<Repo?, RepoResponse>>(named(RESPONSE_CONVERTER_REPO)) { RepoResponseConverter() }

        single<RetrofitRepository> {
            RetrofitRepositoryImpl(
                restApi = get(),
                authorConverter = get(named(RESPONSE_CONVERTER_AUTHOR)),
                repoConverter = get(named(RESPONSE_CONVERTER_REPO))
            )
        }
    }

    fun getNavigationModule() = module {
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

        viewModel { (author: Author, navController: NavController) ->
            DetailsViewModel(
                author = author,
                navController = navController,
                repository = get(),
                roomRepository = get(),
                isOnlineOnStart = get()
            )
        }
    }
}

private const val RESPONSE_CONVERTER_AUTHOR = "AUTHOR"
private const val RESPONSE_CONVERTER_REPO = "REPO"