package com.example.androiddemo.data.reopsitories

import com.example.androiddemo.data.network.ApiInterFace
import com.example.androiddemo.data.network.SafeApiRequest
import com.example.androiddemo.data.network.SharedPref
import com.example.androiddemo.data.responses.LeadProfileAPIResponse

class ProfileRepository(private val api: ApiInterFace, private val pref: SharedPref) :
    SafeApiRequest() {
    suspend fun fetchProfileData(): LeadProfileAPIResponse? {
        return apiRequest {
            api.getLeadProfile("2")
        }
    }

//    fun getLeadProfile(): LeadProfileData? {
//        return pref.getClassObject(
//            AppConstant.PREFERENCE_LEAD_PROFILE,
//            LeadProfileData::class.java
//        ) as LeadProfileData?
//    }
//
//
//    private val profileData = MutableLiveData<LeadProfileAPIResponse>()
//
//    suspend fun getLumperList(): LiveData<LeadProfileAPIResponse> {
//        return withContext(Dispatchers.IO) {
//            fetchLumperList()
//            profileData
//        }
//    }
//
//    private suspend fun fetchLumperList() {
//        val response = apiRequest {
//            api.getLeadProfile("2")
//        }
//        var allList = LeadProfileAPIResponse()
//        response?.let {
//            allList = it
//        }
//        profileData.postValue(allList)
//    }
}