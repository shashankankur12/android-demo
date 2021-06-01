package com.example.androiddemo.viewModelfactory.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androiddemo.data.reopsitories.UserRepository
import com.example.androiddemo.viewmodel.login.LoginViewModel

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory(private val repository: UserRepository) :ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(repository)as T
    }
}