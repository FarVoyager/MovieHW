package com.example.moviehw.data.retrofit

import com.example.moviehw.data.retrofit.converter.MovieListResultObject
import kotlinx.coroutines.flow.Flow

interface RetrofitRepository {

    fun authors(): Flow<List<Author>>

    fun repos(url: String): Flow<List<Repo>>

    fun followers(url: String): Flow<List<Follower>>

    fun movies(): Flow<List<MovieListResultObject>>

}