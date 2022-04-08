package com.example.cstv.matchDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cstv.service.repository.RepositoryTeams

class MatchDetailsViewlModelFactory(private val mRepository: RepositoryTeams) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MatchDetailsViewModel(mRepository) as T
    }
}