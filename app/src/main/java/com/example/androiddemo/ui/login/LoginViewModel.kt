package com.example.androiddemo.ui.login

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddemo.data.reopsitories.UserRepository
import com.example.androiddemo.listner.login.LoginViewModelListener
import com.example.androiddemo.utils.ApiExceptions
import com.example.androiddemo.utils.Coroutines
import com.example.androiddemo.utils.NoInternetException
import kotlinx.coroutines.launch

class LoginViewModel (private val repository: UserRepository) : ViewModel() {
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
                viewModelScope.launch {
                    try {
                        val loginResponse= repository.userLogin(userId!!, password!!)
                        loginResponse?.also {
                            it.token?.let { token->
                                repository.saveToken(token)
                            }
//                            repository.saveUserInPref(it)
                            loginListener?.onSuccess(it)

                        } ?: run{
                            loginResponse?.let { loginListener?.onFailure("No user found") }
                        }

                    }catch (e:ApiExceptions){
                        e.message?.let { loginListener?.onFailure(it) }
                    }catch (e:NoInternetException){
                        e.message?.let { loginListener?.onFailure(it) }
                    }
                }

            }
        }
    }
}