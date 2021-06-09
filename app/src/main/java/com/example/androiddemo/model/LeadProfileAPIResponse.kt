package com.example.androiddemo.model

import com.example.androiddemo.data.responses.BaseResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LeadProfileAPIResponse : BaseResponse() {

    @SerializedName("data")
    @Expose
    var data: LeadProfileData? = null
}