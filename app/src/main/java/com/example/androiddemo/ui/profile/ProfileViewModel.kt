package com.example.androiddemo.ui.profile

import androidx.lifecycle.ViewModel
import com.example.androiddemo.data.reopsitories.ProfileRepository

class ProfileViewModel(repository: ProfileRepository) : ViewModel() {
    var leadProfileData = repository.getLeadProfile()

}