package com.example.moviehw.domain

import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel() {

    abstract fun loadLastData()

    abstract fun updateNetworkStatus(isOnline: Boolean)
}