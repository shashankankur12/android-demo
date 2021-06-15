package com.example.androiddemo.data.reopsitories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.androiddemo.data.network.ApiInterFace
import com.example.androiddemo.data.network.SafeApiRequest
import com.example.androiddemo.data.network.SharedPref
import com.example.androiddemo.data.responses.Data
import com.example.androiddemo.data.responses.LeadProfileAPIResponse
import com.example.androiddemo.data.responses.LeadProfileData
import com.example.androiddemo.utils.AppConstant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProfileRepository(private val api: ApiInterFace, private val pref: SharedPref) : SafeApiRequest() {
    suspend fun profileData(): LeadProfileAPIResponse? {
        return apiRequest { api.getLeadProfile(pref.getString(AppConstant.PREFERENCE_AUTH_TOKEN)) }
    }

    fun getLeadProfile(): LeadProfileData? {
        return pref.getClassObject(AppConstant.PREFERENCE_LEAD_PROFILE, LeadProfileData::class.java) as LeadProfileData?
    }


    private val profileData= MutableLiveData<LeadProfileAPIResponse>()

    suspend fun getLumperList (): LiveData<LeadProfileAPIResponse> {
        return withContext(Dispatchers.IO){
            fetchLumperList()
            profileData
        }
    }

    private suspend fun fetchLumperList(){
        val response= apiRequest {
            api.getLeadProfile("2")
        }
        var allList = LeadProfileAPIResponse()
        response?.let {
           allList= it
        }
        profileData.postValue(allList)
    }
}