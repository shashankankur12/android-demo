package com.example.androiddemo.data.responses

import com.example.androiddemo.utils.Utils
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LumperListAPIResponse : BaseResponse() {
    @SerializedName("data")
    @Expose
    var data: Data? = null

    inner class Data {
        @SerializedName("permanentLumpers")
        @Expose
        var permanentLumpersList: ArrayList<EmployeeData>? = null
            get() = Utils.sortEmployeesList(field)

        @SerializedName("tempLumpers")
        @Expose
        var temporaryLumpers: ArrayList<EmployeeData>? = null
            get() = Utils.sortEmployeesList(field, isTemporaryLumpers = true)
    }
}