package com.example.androiddemo.ui.login

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddemo.data.reopsitories.UserRepository
import com.example.androiddemo.data.responses.Data
import com.example.androiddemo.data.responses.LoginUserData
import com.example.androiddemo.listner.login.ViewModelListener
import com.example.androiddemo.utils.ApiExceptions
import com.example.androiddemo.utils.NoInternetException
import com.example.androiddemo.utils.Resource
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UserRepository) : ViewModel() {
    var userId: String? = null
    var password: String? = null
    var loginListener: ViewModelListener? = null

    private val loginUserData = MutableLiveData<Resource<LoginUserData>>()

    fun getLoginData(): LiveData<Resource<LoginUserData>> {
        return loginUserData
    }

    fun onSignInButtonClick(view: View) {
        loginUserData.postValue(Resource.loading())
        when {
            userId.isNullOrEmpty() -> {
                loginUserData.postValue(Resource.error("Please enter UserId"))
            }
            password.isNullOrEmpty() -> {
                loginUserData.postValue(Resource.error("Please enter Password"))
            }
            else -> {
                viewModelScope.launch {
                    try {
                        val loginResponse = repository.userLogin(userId!!, password!!)
                        loginResponse?.also {
                            it.token?.let { token ->
                                repository.saveToken(token)
                            }
//                            repository.saveUserInPref(it)
                            loginUserData.postValue(Resource.success(it))

                        } ?: run {
                            loginUserData.postValue(Resource.error("No user found"))
                        }

                    } catch (e: ApiExceptions) {
                        loginUserData.postValue(Resource.error(e.message))
                    } catch (e: NoInternetException) {
                        loginUserData.postValue(Resource.error(e.message))
                    }
                }

            }
        }
    }
}