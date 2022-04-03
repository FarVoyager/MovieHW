package com.example.moviehw.domain

import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel() {

    abstract fun updateNetworkStatus(isOnline: Boolean)
}