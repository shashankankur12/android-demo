package com.example.androiddemo.data.reopsitories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.androiddemo.data.network.ApiInterFace
import com.example.androiddemo.model.LoginRequest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {

    fun userLogin(userId:String , password: String): LiveData<String>{
        val loginResponse =MutableLiveData<String>()
        val loginRequest= LoginRequest(userId, password)
        ApiInterFace().doLogin(loginRequest)
            .enqueue(object :Callback<ResponseBody>{
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.isSuccessful){
                        loginResponse.value=response.body()?.string()
                    }else  loginResponse.value=response.errorBody()?.string()
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    loginResponse.value= t.message
                }

            })
        return loginResponse
    }
}