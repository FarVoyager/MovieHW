package com.example.moviehw.data.retrofit

import kotlinx.coroutines.flow.Flow

interface RetrofitRepository {

    fun authors(): Flow<List<Author>>

    fun repos(url: String): Flow<List<Repo>>

    fun followers(url: String): Flow<List<Follower>>

}