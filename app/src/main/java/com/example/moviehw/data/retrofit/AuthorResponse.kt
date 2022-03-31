package com.example.moviehw.data.retrofit

import com.google.gson.annotations.Expose

data class AuthorResponse(

    @Expose val id: String? = null,
    @Expose val login: String? = null,
    @Expose val avatarUrl: String? = null,
    @Expose val reposUrl: String? = null,
    @Expose val subscriptionsUrl: String? = null,

)

