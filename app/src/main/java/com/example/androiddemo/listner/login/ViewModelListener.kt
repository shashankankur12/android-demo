package com.example.androiddemo.listner.login

import androidx.lifecycle.LiveData
import com.example.androiddemo.data.responses.LoginUserData

interface ViewModelListener {
    fun onStarted()
    fun onSuccess(loginResponse: String?)
    fun onFailure(message :String?)
}