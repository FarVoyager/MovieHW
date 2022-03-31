package com.example.moviehw.data.retrofit.converter

import com.example.moviehw.data.retrofit.Author
import com.example.moviehw.data.retrofit.AuthorResponse

class AuthorResponseConverter: ResponseConverter<Author?, AuthorResponse> {

    override fun convert(response: AuthorResponse): Author? {
        response.id ?: return null
        response.login ?: return null
        response.avatarUrl ?: return null
        response.reposUrl ?: return null
        response.subscriptionsUrl ?: return null

        return Author(
            id = response.id,
            login = response.login,
            avatarUrl = response.avatarUrl,
            reposUrl = response.reposUrl,
            subscriptionsUrl = response.subscriptionsUrl
        )
    }
}