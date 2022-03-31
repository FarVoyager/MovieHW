package com.example.moviehw.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomRepo(
    @PrimaryKey(autoGenerate = false) val id: String,
    @ColumnInfo val authorId: String,
    @ColumnInfo val name: String
)
