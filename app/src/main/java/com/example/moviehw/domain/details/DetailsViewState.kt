package com.example.moviehw.domain.details

import com.example.moviehw.data.retrofit.Repo

data class DetailsViewState(
    val isLoading: Boolean = true,
    val reposList: List<Repo> = listOf(),
    val followersQty: Int = 0,
    val isOfflineLoaded: Boolean = false,
    val isOfflineDataEmpty: Boolean = false,
    val isOnline: Boolean = true
)
