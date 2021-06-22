package com.example.androiddemo.data.network

import android.content.Context
import android.content.SharedPreferences
import com.example.androiddemo.utils.AppConstant
import com.example.androiddemo.utils.AppConstant.Companion.PREFERENCE_NAME
import com.google.gson.Gson

class SharedPref(context: Context) : AppConstant {

    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCE_NAME, 0)
    private var editor: SharedPreferences.Editor = sharedPreferences.edit()

    init {
        editor = sharedPreferences.edit()
    }


    var token: String = sharedPreferences.getString(AppPref.PREFERENCE_AUTH_TOKEN.toString(), "")!!
        set(value: String) {
            editor.putString(AppPref.PREFERENCE_AUTH_TOKEN.toString(), value)
            editor.apply()
        }

    private enum class AppPref {
        PREFERENCE_AUTH_TOKEN
    }

}