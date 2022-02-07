package com.example.testexercise.data.room.converter

import com.example.testexercise.data.retrofit.Repo
import com.example.testexercise.data.room.RoomRepo

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