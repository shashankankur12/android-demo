package com.example.androiddemo.data.network

import com.example.androiddemo.model.LoginRequest
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterFace {

    @POST("employees/lead/login")
    fun doLogin(@Body request: LoginRequest): Call<ResponseBody>

    companion object{
        operator  fun invoke():ApiInterFace{
            return Retrofit.Builder()
                .baseUrl("https://dev.quickhands.api-build-release.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiInterFace::class.java)
        }
    }

}