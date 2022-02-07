package com.example.testexercise.domain.details

import com.example.testexercise.data.retrofit.Repo

data class DetailsViewState(
    val isLoading: Boolean = true,
    val reposList: List<Repo> = listOf(),
    val followersQty: Int = 0,
    val isOfflineLoaded: Boolean = false,
    val isOnline: Boolean = true
)
