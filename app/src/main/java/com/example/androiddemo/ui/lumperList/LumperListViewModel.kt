package com.example.androiddemo.ui.lumperList

import androidx.lifecycle.ViewModel
import com.example.androiddemo.data.reopsitories.LumperListRepository
import com.example.androiddemo.utils.lazyDeferred

class LumperListViewModel(repo :LumperListRepository) : ViewModel() {

    val lumperList by lazyDeferred { repo.getLumperList() }
}