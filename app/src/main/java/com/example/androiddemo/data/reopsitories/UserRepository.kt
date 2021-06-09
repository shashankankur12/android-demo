package com.example.androiddemo.data.reopsitories

import com.example.androiddemo.data.network.ApiInterFace
import com.example.androiddemo.data.network.SafeApiRequest
import com.example.androiddemo.data.network.SharedPref
import com.example.androiddemo.data.responses.LoginResponse
import com.example.androiddemo.data.responses.LoginUserData
import com.example.androiddemo.model.LoginRequest
import com.example.androiddemo.utils.AppConstant

class UserRepository(private val api: ApiInterFace , private val pref:SharedPref)  :SafeApiRequest(){

    suspend fun userLogin(userId: String, password: String): LoginResponse? {
        val loginRequest = LoginRequest(userId, password, "cfu6Tm7WQEmdUJWDESOxHB:APA91bEKufluvQgwQnnJV8skdVwHF7M_9zZ96qz4UBkHTGprg92upqNbaFSwWf0xsCxA1CMVSgZ78ke5QhtvLf90Agc-Y1MvQ5XVSG6s3Q_6LpaY2DJiu87rIQNywWrW4aWhvyLwwIK0")
        return apiRequest { api.doLogin(loginRequest) }
    }

    fun saveToken(token: String) {
        pref.setString(AppConstant.PREFERENCE_AUTH_TOKEN, token)
    }

    fun saveUserInPref(loginUserData: LoginUserData) {
        pref.setClassObject(AppConstant.PREFERENCE_LEAD_PROFILE, loginUserData)
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