package com.example.androiddemo.data.reopsitories

import com.example.androiddemo.data.network.ApiInterFace
import com.example.androiddemo.data.network.SafeApiRequest
import com.example.androiddemo.data.network.SharedPref
import com.example.androiddemo.model.LeadProfileAPIResponse
import com.example.androiddemo.model.LeadProfileData
import com.example.androiddemo.utils.AppConstant

class ProfileRepository(private val api: ApiInterFace, private val pref: SharedPref) : SafeApiRequest() {
    suspend fun profileData(): LeadProfileAPIResponse? {
        return apiRequest { api.getLeadProfile(pref.getString(AppConstant.PREFERENCE_AUTH_TOKEN)) }
    }

    fun getLeadProfile(): LeadProfileData? {
        return pref.getClassObject(AppConstant.PREFERENCE_LEAD_PROFILE, LeadProfileData::class.java) as LeadProfileData?
    }
}