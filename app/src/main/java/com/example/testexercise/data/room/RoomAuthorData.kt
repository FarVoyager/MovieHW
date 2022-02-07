package com.example.testexercise.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomAuthorData(
    @PrimaryKey(autoGenerate = false) val id: String,
    @ColumnInfo val login: String,
    @ColumnInfo val avatarUrl: String,
    @ColumnInfo val reposUrl: String,
    @ColumnInfo val subscriptionsUrl: String,
    @ColumnInfo val subscriptionsQty: Int,
    )
