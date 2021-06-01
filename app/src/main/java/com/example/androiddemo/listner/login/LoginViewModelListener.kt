package com.example.androiddemo.listner.login

import androidx.lifecycle.LiveData
import com.example.androiddemo.data.responses.LoginUserData

interface LoginViewModelListener {
    fun onStarted()
    fun onSuccess(loginResponse: LoginUserData?)
    fun onFailure(message :String)
}