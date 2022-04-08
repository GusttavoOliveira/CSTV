package com.example.cstv.matchList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cstv.service.repository.RepositoryMatches

class MatchesListViewModelFactory(private val mRespository: RepositoryMatches) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MatchesListViewModel(mRespository) as T
    }
}