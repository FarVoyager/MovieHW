package com.example.testexercise.data.room

import com.example.testexercise.data.retrofit.Author
import com.example.testexercise.data.retrofit.Repo
import com.example.testexercise.data.room.converter.AuthorDataConverter
import com.example.testexercise.data.room.converter.RepoConverter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RoomRepositoryImpl(
    private val db: Database,
    private val authorConverter: AuthorDataConverter,
    private val repoConverter: RepoConverter,

    ): RoomRepository {
    override suspend fun getAuthors(): Flow<List<Author>> = flow {
        val authorsList = mutableListOf<Author>()
        db.roomAuthorDao.getAll().forEach {
            authorConverter.convertFromRoomEntity(it).apply {
                authorsList.add(this)
            }
        }
        emit(authorsList)
    }

    override suspend fun getAuthorSubscriptionsQtyByAuthorId(authorId: String): Flow<Int> = flow {
        emit(db.roomAuthorDao.getAuthorSubscriptionsQtyByAuthorId(authorId))
    }

    override suspend fun insertAuthors(list: List<Author>) {
        list.forEach {
            db.roomAuthorDao.insert(authorConverter.convertToRoomEntity(it).apply {
            })
        }
    }

    override suspend fun insertRepos(list: List<Repo>, authorId: String) {
        list.forEach {
            db.roomRepoDao.insert(repoConverter.convertToRoomEntity(it, authorId))
        }
    }

    override suspend fun getReposByAuthorId(authorId: String): Flow<List<Repo>> = flow {
        val repoList = mutableListOf<Repo>()
        db.roomRepoDao.getReposByAuthorId(authorId).forEach {
            repoConverter.convertFromRoomEntity(it).apply {
                repoList.add(this)
            }
        }
        emit(repoList)
    }

    override suspend fun updateSubscriptionsQtyByAuthorId(qty: Int, authorId: String) {
            db.roomAuthorDao.updateSubscriptionsQtyByAuthorId(qty, authorId)
    }

    override suspend fun getAllRepos(): Flow<List<Repo>> = flow {
        val repoList = mutableListOf<Repo>()
        db.roomRepoDao.getAll().forEach {
            repoConverter.convertFromRoomEntity(it).apply {
                repoList.add(this)
            }
        }
        emit(repoList)
    }

}