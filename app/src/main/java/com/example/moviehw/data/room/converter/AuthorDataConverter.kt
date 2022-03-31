package com.example.moviehw.data.room.converter

import com.example.moviehw.data.retrofit.Author
import com.example.moviehw.data.room.RoomAuthorData

class AuthorDataConverter {
    fun convertToRoomEntity(author: Author) =
        RoomAuthorData(
            id = author.id,
            login = author.login,
            avatarUrl = author.avatarUrl,
            reposUrl = author.reposUrl,
            subscriptionsUrl = author.subscriptionsUrl,
            subscriptionsQty = 0
        )



    fun convertFromRoomEntity(roomAuthorData: RoomAuthorData) =
        Author(
            id = roomAuthorData.id,
            login = roomAuthorData.login,
            avatarUrl = roomAuthorData.avatarUrl,
            reposUrl = roomAuthorData.reposUrl,
            subscriptionsUrl = roomAuthorData.subscriptionsUrl
        )

}