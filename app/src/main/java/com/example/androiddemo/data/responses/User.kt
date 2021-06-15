package com.example.androiddemo.data.responses

import com.google.gson.annotations.SerializedName

   
data class User (

    @SerializedName("data") var data : Data,
    @SerializedName("support") var support : Support

)