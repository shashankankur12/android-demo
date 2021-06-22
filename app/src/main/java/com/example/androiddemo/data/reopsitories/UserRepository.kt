package com.example.androiddemo.data.reopsitories

import com.example.androiddemo.data.network.ApiInterFace
import com.example.androiddemo.data.network.SafeApiRequest
import com.example.androiddemo.data.network.SharedPref
import com.example.androiddemo.data.responses.LoginUserData
import com.example.androiddemo.data.responses.LoginRequest
import com.example.androiddemo.utils.AppConstant

class UserRepository(private val api: ApiInterFace )  :SafeApiRequest(){
    suspend fun userLogin(userId: String, password: String): LoginUserData? =
        apiRequest { api.doLogin(LoginRequest(userId, password)) }
}