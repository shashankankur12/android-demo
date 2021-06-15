package com.example.androiddemo.data.reopsitories

import com.example.androiddemo.data.network.ApiInterFace
import com.example.androiddemo.data.network.SafeApiRequest
import com.example.androiddemo.data.network.SharedPref
import com.example.androiddemo.data.responses.LoginResponse
import com.example.androiddemo.data.responses.LoginUserData
import com.example.androiddemo.data.responses.LoginRequest
import com.example.androiddemo.utils.AppConstant

class UserRepository(private val api: ApiInterFace , private val pref:SharedPref)  :SafeApiRequest(){

    suspend fun userLogin(userId: String, password: String): LoginUserData? {
        val loginRequest = LoginRequest(userId, password)
        return apiRequest { api.doLogin(loginRequest) }
    }

    fun saveToken(token: String) {
        pref.setString(AppConstant.PREFERENCE_AUTH_TOKEN, token)
    }

    fun saveUserInPref(loginUserData: LoginUserData) {
        pref.setClassObject(AppConstant.PREFERENCE_LEAD_PROFILE, loginUserData)
    }

}