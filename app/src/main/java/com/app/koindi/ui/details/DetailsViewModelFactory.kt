package com.app.koindi.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.koindi.models.responses.MovieModel
import com.app.koindi.network.datasource.details.DetailRepository

class DetailsViewModelFactory(private val mDetailRepository: DetailRepository,private val movieItem : MovieModel) :
    ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsViewModel(mDetailRepository,movieItem) as T
    }
}