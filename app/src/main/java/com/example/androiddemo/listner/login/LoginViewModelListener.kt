package com.example.androiddemo.listner.login

import androidx.lifecycle.LiveData

interface LoginViewModelListener {
    fun onStarted()
    fun onSuccess(loginResponse: LiveData<String>)
    fun onFailure(message :String)
}