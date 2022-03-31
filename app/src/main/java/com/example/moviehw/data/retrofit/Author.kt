package com.example.moviehw.data.retrofit

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class Author(
    @Expose val id: String,
    @Expose val login: String,
    @Expose val avatarUrl: String,
    @Expose val reposUrl: String,
    @Expose val subscriptionsUrl: String,
    ): Parcelable
