package com.example.androiddemo.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddemo.data.network.SharedPref
import com.example.androiddemo.data.reopsitories.UserRepository
import com.example.androiddemo.data.responses.LoginUserData
import com.example.androiddemo.utils.ApiExceptions
import com.example.androiddemo.utils.NoInternetException
import com.example.androiddemo.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UserRepository, private val pref: SharedPref) :
    ViewModel() {

    private val loginUserData = MutableLiveData<Resource<LoginUserData>>()

    fun getLoginData(): LiveData<Resource<LoginUserData>> {
        return loginUserData
    }

    fun onSignInButtonClick(userId: String, password: String) {
        loginUserData.postValue(Resource.loading())
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val loginResponse = repository.userLogin(userId, password)
                loginResponse?.also {
                    it.token?.let { token ->
                        pref.token = token
                        loginUserData.postValue(Resource.success(it))
                    }
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