package com.example.androiddemo.viewmodel.login

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.androiddemo.data.reopsitories.UserRepository
import com.example.androiddemo.listner.login.LoginViewModelListener

class LoginViewModel : ViewModel() {
    var userId: String? = null
    var password: String? =null
    var loginListener :LoginViewModelListener ?=null

    fun onSignInButtonClick(view: View){
        loginListener?.onStarted()
        when {
            userId.isNullOrEmpty() -> {
                loginListener?.onFailure("Please enter UserId")

            }
            password.isNullOrEmpty() -> {
                loginListener?.onFailure("Please enter Password")
            }
            else -> {
                val loginResponse= UserRepository().userLogin(userId!!, password!!)
                loginListener?.onSuccess(loginResponse)
            }
        }
    }
}