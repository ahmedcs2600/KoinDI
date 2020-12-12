package com.app.koindi.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.koindi.network.datasource.home.HomeRepository

class HomeViewModelFactory(private val mHomeRepository: HomeRepository) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(mHomeRepository) as T
    }
}