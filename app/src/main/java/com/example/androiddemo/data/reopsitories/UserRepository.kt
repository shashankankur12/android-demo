package com.example.androiddemo.data.reopsitories

import com.example.androiddemo.data.network.ApiInterFace
import com.example.androiddemo.data.network.SafeApiRequest
import com.example.androiddemo.data.responses.LoginResponse
import com.example.androiddemo.model.LoginRequest

class UserRepository(private val api: ApiInterFace)  :SafeApiRequest(){

    suspend fun userLogin(userId: String, password: String): LoginResponse? {
        val loginRequest = LoginRequest(userId, password, "eQZCHpD2QPiWyo6TRp6Oqo:APA91bE2BaMilunJI6gmLro6x_fbBfn6agwgUxpZbBNsV9498SS0ER-6jZoRtT4e8R5HbAD7LJb22BenJg4wW1DekGYrr0nPkZIWkN5mPx-WxTw89YlqOImq_JWL-BcYHhBzMUzX6-37")
        return apiRequest { api.doLogin(loginRequest) }
    }

//    private fun getToken(): String {
//        var token =""
//        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener { task ->
//            if (task.isSuccessful) { token = task.result?.token!!
//            }
//        }
//        return token
//    }
}