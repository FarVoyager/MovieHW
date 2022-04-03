package com.example.moviehw.domain.mainlist

import com.example.moviehw.data.retrofit.converter.MovieListResultObject

data class MainListViewState(
    val isLoading: Boolean = true,
    val isOnline: Boolean = true,
    val isOfflineLoaded: Boolean = false,

    val moviesList: List<MovieListResultObject> = listOf()
)
