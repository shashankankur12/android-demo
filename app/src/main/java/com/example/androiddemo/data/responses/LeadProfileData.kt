package com.example.androiddemo.data.responses

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LeadProfileData : EmployeeData(), Parcelable {
    @SerializedName("building")
    @Expose
    var buildingDetailData: ArrayList<BuildingDetailData>? = null
}