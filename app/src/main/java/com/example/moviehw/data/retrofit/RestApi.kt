package com.example.moviehw.data.retrofit

import retrofit2.http.GET
import retrofit2.http.Url

interface RestApi {
    @GET("/users")
    suspend fun getUsers(): List<AuthorResponse>

    @GET
    suspend fun getUserRepos(@Url url: String): List<RepoResponse>

    @GET
    suspend fun getUserFollowers(@Url url: String): List<Follower>
}