package com.example.moviehw.data.room

import com.example.moviehw.data.retrofit.Author
import com.example.moviehw.data.retrofit.Repo
import kotlinx.coroutines.flow.Flow

interface RoomRepository {

    suspend fun getAuthors(): Flow<List<Author>>

    suspend fun getAuthorSubscriptionsQtyByAuthorId(authorId: String): Flow<Int>

    suspend fun insertAuthors(list: List<Author>)

    suspend fun getReposByAuthorId(authorId: String): Flow<List<Repo>>

    suspend fun insertRepos(list: List<Repo>, authorId: String)

    suspend fun getAllRepos(): Flow<List<Repo>>

    suspend fun updateSubscriptionsQtyByAuthorId(qty: Int, authorId: String)

}