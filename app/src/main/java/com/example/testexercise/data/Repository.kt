package com.example.testexercise.data

import kotlinx.coroutines.flow.Flow

interface Repository {

    fun authors(): Flow<List<Author>>

    fun repos(url: String): Flow<List<Repo>>

    fun followers(url: String): Flow<List<Follower>>

}