package com.example.testexercise.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RoomAuthorData::class, RoomRepo::class], version = 5, exportSchema = true)
abstract class Database: RoomDatabase() {
    abstract val roomAuthorDao: RoomAuthorDao
    abstract val roomRepoDao: RoomRepoDao
}