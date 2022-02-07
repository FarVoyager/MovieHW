package com.example.testexercise.domain.mainlist

import android.net.Uri
import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.testexercise.data.retrofit.Author
import com.example.testexercise.data.retrofit.RetrofitRepository
import com.example.testexercise.data.room.RoomRepository
import com.example.testexercise.utils.NavDestinations
import com.example.testexercise.domain.BaseViewModel
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

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
            repository.authors().catch {
                Log.d("Exception", "Exception while loading github authors: ${it.message}")
            }.collect { list ->
                if (list.isEmpty()) Log.d(
                    "Unexpected Behavior",
                    "Received data is empty. Maybe some of Author fields are null"
                )
                roomRepository.insertAuthors(list)
                _viewState.update {
                    it.copy(authorsList = list, isLoading = false)
                }
            }
        }
    }

    private fun loadOfflineData() {
        viewModelScope.launch(Dispatchers.IO) {
            roomRepository.getAuthors().collect { roomList ->
                _viewState.update {
                    it.copy(authorsList = roomList, isLoading = false, isOfflineLoaded = true)
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

    override fun loadLastData() {
        loadOfflineData()
    }

    fun onItemSelected(author: Author) {
        val json = Uri.encode(Gson().toJson(author))
        navController.navigate("${navDestinations.DETAILS_ROUTE}/$json")
    }
}