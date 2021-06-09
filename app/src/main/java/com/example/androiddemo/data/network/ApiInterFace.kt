package com.example.androiddemo.data.network

import androidx.lifecycle.viewmodel.savedstate.R
import com.example.androiddemo.data.responses.LoginResponse
import com.example.androiddemo.model.LeadProfileAPIResponse
import com.example.androiddemo.model.LoginRequest
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterFace {

    @POST("employees/lead/login")
    suspend fun doLogin(@Body request: LoginRequest): Response<LoginResponse>

    @GET("employees/me")
    suspend fun getLeadProfile(@Header("Authorization") auth: String): Response<LeadProfileAPIResponse>

    companion object {
        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor): ApiInterFace {
            val okHttpClient=OkHttpClient.Builder().addInterceptor(networkConnectionInterceptor).build()
            return Retrofit.Builder()
                .client(okHttpClient)
                    .baseUrl("https://dev.quickhands.api-build-release.com/v1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiInterFace::class.java)
        }
    }

}