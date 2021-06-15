package com.example.androiddemo.ui.lumperList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androiddemo.data.reopsitories.LumperListRepository

@Suppress("UNCHECKED_CAST")
class LumperListViewModelFactory(private val repository: LumperListRepository) :ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LumperListViewModel(repository)as T
    }
}