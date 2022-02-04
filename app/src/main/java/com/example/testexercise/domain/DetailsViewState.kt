package com.example.testexercise.domain

import com.example.testexercise.data.Author
import com.example.testexercise.data.Repo

data class DetailsViewState(
    val isLoading: Boolean = true,
    val reposList: List<Repo> = listOf(),
    val followersQty: Int = 0
)
