package com.example.testexercise.domain

import com.example.testexercise.data.Author

data class MainListViewState(
    val isLoading: Boolean = true,
    val isOnline: Boolean = true,
    val authorsList: List<Author> = listOf()
)
