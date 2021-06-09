package com.example.androiddemo.model

import android.os.Parcelable
import com.example.androiddemo.data.responses.EmployeeData
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LeadProfileData : EmployeeData(), Parcelable {
    @SerializedName("building")
    @Expose
    var buildingDetailData: ArrayList<BuildingDetailData>? = null
}