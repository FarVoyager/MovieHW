package com.example.moviehw.domain.mainlist

import com.example.moviehw.data.retrofit.Author

data class MainListViewState(
    val isLoading: Boolean = true,
    val isOnline: Boolean = true,
    val isOfflineLoaded: Boolean = false,

    val authorsList: List<Author> = listOf()
)
