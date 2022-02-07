package com.example.testexercise.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface RoomAuthorDao {

    @Query("SELECT * FROM RoomAuthorData")
    fun getAll(): List<RoomAuthorData>

    @Query("SELECT subscriptionsQty FROM RoomAuthorData WHERE id=:authorId")
    fun getAuthorSubscriptionsQtyByAuthorId(authorId: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg data: RoomAuthorData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: RoomAuthorData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: List<RoomAuthorData>)


    @Query("UPDATE RoomAuthorData SET subscriptionsQty=:qty WHERE id=:authorId")
    fun updateSubscriptionsQtyByAuthorId(qty: Int, authorId: String)

    @Update
    fun update(vararg data: RoomAuthorData)

    @Delete
    fun delete(vararg data: RoomAuthorData)

    @Query("DELETE FROM RoomAuthorData")
    fun deleteAll()
}