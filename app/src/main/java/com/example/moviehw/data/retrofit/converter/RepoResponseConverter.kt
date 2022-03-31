package com.example.moviehw.data.retrofit.converter

import com.example.moviehw.data.retrofit.Repo
import com.example.moviehw.data.retrofit.RepoResponse

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