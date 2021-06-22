package com.example.androiddemo.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androiddemo.data.network.SharedPref
import com.example.androiddemo.data.reopsitories.UserRepository

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory(private val repository: UserRepository, private val pref: SharedPref) :ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(repository ,pref) as T
    }
}