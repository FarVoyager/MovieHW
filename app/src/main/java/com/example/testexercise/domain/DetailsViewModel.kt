package com.example.testexercise.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testexercise.data.Author
import com.example.testexercise.data.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val author: Author,
    private val repository: Repository,
    ): ViewModel() {
    private val _viewState = MutableStateFlow(DetailsViewState())
    val viewState: StateFlow<DetailsViewState> = _viewState

    init {
        loadRepos()
    }

    private fun loadRepos() {
        viewModelScope.launch {
            repository.repos(author.reposUrl).catch {
                println("Error while loading repos: ${it.message}")
            }.collect { repos ->
                _viewState.update {
                    it.copy(reposList = repos, isLoading = false)
                }
                repository.followers(author.subscriptionsUrl).catch {
                    println("Error while loading followers: ${it.message}")
                }.collect { followers ->
                    _viewState.update {
                        it.copy(followersQty = followers.size)
                    }
                }
            }
        }
    }
}