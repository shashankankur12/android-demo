package com.example.androiddemo.data.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LeadProfileAPIResponse  {

    @SerializedName("data")
    @Expose
    var data: Data? = null

    @SerializedName("support")
    @Expose
    var support: Support? = null
}