package com.example.moviehw.data.retrofit

import com.example.moviehw.data.retrofit.converter.MovieListResultObject
import retrofit2.http.GET
import retrofit2.http.Url

interface RestApi {
    @GET("/users")
    suspend fun getUsers(): List<AuthorResponse>

    @GET
    suspend fun getUserRepos(@Url url: String): List<RepoResponse>

    @GET
    suspend fun getUserFollowers(@Url url: String): List<Follower>

    @GET("/3/movie/popular?")
    suspend fun getMovies(): List<MovieListResultObject>
}