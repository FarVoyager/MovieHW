package com.example.testexercise.domain

import androidx.lifecycle.ViewModel
import android.net.Uri
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import com.example.testexercise.data.Author
import com.example.testexercise.data.Repository
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainListViewModel(
    private val repository: Repository,
    private val navController: NavHostController,
    private val navDestinations: NavDestinations
) : ViewModel() {

    private val _viewState = MutableStateFlow(MainListViewState())
    val viewState: StateFlow<MainListViewState> = _viewState

    init {
        loadAuthors()
    }

    private fun loadAuthors() {
        viewModelScope.launch {
            repository.authors().collect { list ->
                _viewState.update {
                    it.copy(authorsList = list, isLoading = false)
                }
            }
        }
    }

    fun onItemSelected(author: Author) {
        val json = Uri.encode(Gson().toJson(author))
        println("$json VVV")
        navController.navigate("${navDestinations.DETAILS_ROUTE}/$json")
    }
}