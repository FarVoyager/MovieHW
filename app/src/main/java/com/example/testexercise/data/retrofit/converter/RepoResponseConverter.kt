package com.example.testexercise.data.retrofit.converter

import com.example.testexercise.data.retrofit.Author
import com.example.testexercise.data.retrofit.AuthorResponse
import com.example.testexercise.data.retrofit.Repo
import com.example.testexercise.data.retrofit.RepoResponse

class RepoResponseConverter: ResponseConverter<Repo?, RepoResponse> {

    override fun convert(response: RepoResponse): Repo? {
        response.id ?: return null
        response.name ?: return null

        return Repo(
            id = response.id,
            name = response.name,
        )
    }
}