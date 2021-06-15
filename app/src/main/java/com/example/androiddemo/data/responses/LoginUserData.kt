package com.example.androiddemo.data.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class LoginUserData :  Serializable {
    @SerializedName("token")
    @Expose
    var token: String? = null
}