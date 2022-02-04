package com.example.testexercise.data

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url
import kotlinx.coroutines.flow.Flow

interface RestApi {
    @GET("/users")
    suspend fun getUsers(): List<Author>

    @GET
    suspend fun getUserRepos(@Url url: String): List<Repo>
}