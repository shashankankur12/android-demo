package com.example.androiddemo.data.reopsitories

import com.example.androiddemo.data.network.ApiInterFace
import com.example.androiddemo.data.network.SafeApiRequest
import com.example.androiddemo.data.network.SharedPref
import com.example.androiddemo.data.responses.UserList

class LumperListRepository(private val api: ApiInterFace) :
    SafeApiRequest() {
    suspend fun fetchAllLumperData(): UserList? {
        return apiRequest {
            api.getAllLumpersData("1")
        }
    }
}