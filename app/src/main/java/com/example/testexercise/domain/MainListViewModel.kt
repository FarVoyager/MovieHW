package com.example.testexercise.domain

import android.content.Context
import androidx.lifecycle.ViewModel
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import com.example.testexercise.data.Author
import com.example.testexercise.data.Repository
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainListViewModel(
    private val navController: NavController,
    private val repository: Repository,
    private val navDestinations: NavDestinations
) : ViewModel() {

    private val _viewState = MutableStateFlow(MainListViewState())
    val viewState: StateFlow<MainListViewState> = _viewState

    init {
        loadAuthors()
    }

    private fun loadAuthors() {

            viewModelScope.launch {
                repository.authors().catch {
                    println("Exception while loading github authors: ${it.message}")
                }.collect { list ->
                    _viewState.update {
                        it.copy(authorsList = list, isLoading = false)
                    }
                }
            }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun checkInternetConnection(context: Context) {
        _viewState.update {
            it.copy(isOnline = isOnline(context))
        }
        if (_viewState.value.isOnline) loadAuthors()
    }

    fun onItemSelected(author: Author) {
        val json = Uri.encode(Gson().toJson(author))
        navController.navigate("${navDestinations.DETAILS_ROUTE}/$json")
    }
}