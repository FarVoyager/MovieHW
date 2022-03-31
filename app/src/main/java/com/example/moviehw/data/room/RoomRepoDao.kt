package com.example.moviehw.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RoomRepoDao {

    @Query("SELECT * FROM RoomRepo")
    fun getAll(): List<RoomRepo>

    @Query("SELECT * FROM RoomRepo WHERE authorId=:id")
    fun getReposByAuthorId(id: String): List<RoomRepo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg data: RoomRepo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: RoomRepo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: List<RoomRepo>)

    @Delete
    fun delete(vararg data: RoomRepo)

    @Query("DELETE FROM RoomRepo")
    fun deleteAll()
}