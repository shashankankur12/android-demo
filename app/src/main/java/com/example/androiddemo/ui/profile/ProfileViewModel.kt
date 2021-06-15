package com.example.androiddemo.ui.profile

import androidx.lifecycle.ViewModel
import com.example.androiddemo.data.reopsitories.ProfileRepository
import com.example.androiddemo.utils.lazyDeferred

class ProfileViewModel(repository: ProfileRepository) : ViewModel() {
    val lumperList by lazyDeferred {
        repository.getLumperList()

    }
}