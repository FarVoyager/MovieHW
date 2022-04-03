package com.example.moviehw.domain.mainlist

import android.net.Uri
import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.moviehw.BuildConfig
import com.example.moviehw.data.retrofit.Author
import com.example.moviehw.data.retrofit.RetrofitRepository
import com.example.moviehw.data.retrofit.converter.MovieListResultObject
import com.example.moviehw.data.room.RoomRepository
import com.example.moviehw.utils.NavDestinations
import com.example.moviehw.domain.BaseViewModel
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val KEY = BuildConfig.API_KEY

class MainListViewModel(
    private val navController: NavController,
    private val repository: RetrofitRepository,
    private val roomRepository: RoomRepository,
    private val navDestinations: NavDestinations,
    private val isOnlineOnStart: Boolean
) : BaseViewModel() {

    private val _viewState = MutableStateFlow(MainListViewState())
    val viewState: StateFlow<MainListViewState> = _viewState

    init {
        updateNetworkStatus(isOnlineOnStart)
    }

    private fun loadOnlineData() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.movies().catch {
                Log.d("Exception", "Exception while loading movies: ${it.message}")
            }.collect { list ->
                if (list.isEmpty()) Log.d(
                    "Unexpected Behavior",
                    "Received data is empty. Maybe some of Movie fields are null"
                )
                _viewState.update {
                    it.copy(moviesList = list, isLoading = false)
                }
            }
        }
    }

    override fun updateNetworkStatus(isOnline: Boolean) {
        _viewState.update {
            it.copy(isOnline = isOnline)
        }
        if (_viewState.value.isOnline) {
            loadOnlineData()
        }
    }

    fun onItemSelected(movie: MovieListResultObject) {
        val json = Uri.encode(Gson().toJson(movie))
        navController.navigate("${navDestinations.DETAILS_ROUTE}/$json")
    }
}