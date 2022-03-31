package com.example.moviehw.data.room.converter

import com.example.moviehw.data.retrofit.Repo
import com.example.moviehw.data.room.RoomRepo

class RepoConverter {
        fun convertToRoomEntity(repo: Repo, authorId: String) =
            RoomRepo(
                id = repo.id,
                authorId = authorId,
                name = repo.name
            )

        fun convertFromRoomEntity(roomRepo: RoomRepo) =
            Repo(name = roomRepo.name, id = roomRepo.id)
}