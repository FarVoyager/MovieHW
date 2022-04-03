package com.example.moviehw.data.retrofit

import com.example.moviehw.data.retrofit.converter.MovieListResultObject
import com.example.moviehw.data.retrofit.converter.ResponseConverter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RetrofitRepositoryImpl(
    private val restApi: RestApi,
    private val authorConverter: ResponseConverter<Author?, AuthorResponse>,
    private val repoConverter: ResponseConverter<Repo?, RepoResponse>,
    ) : RetrofitRepository {
    override fun authors(): Flow<List<Author>> =
        flow {
            emit(restApi.getUsers().mapNotNull {
                authorConverter.convert(it)
            })
        }

    override fun repos(url: String): Flow<List<Repo>> =
        flow {
            emit(restApi.getUserRepos(url).mapNotNull(repoConverter::convert))
        }

    override fun followers(url: String): Flow<List<Follower>> = flow {
        emit(restApi.getUserFollowers(url))
    }

    override fun movies(): Flow<List<MovieListResultObject>> =
        flow {
            emit(restApi.getMovies())
        }
}