package com.example.testexercise.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepositoryImpl(
    private val restApi: RestApi,
) : Repository {
    override fun authors(): Flow<List<Author>> =
        flow {
            emit(restApi.getUsers())
        }

    override fun repos(url: String): Flow<List<Repo>> =
        flow {
            emit(restApi.getUserRepos(url))
        }

    override fun followers(url: String): Flow<List<Follower>> = flow {
        emit(restApi.getUserFollowers(url))
    }
}