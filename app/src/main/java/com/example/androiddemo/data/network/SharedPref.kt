package com.example.androiddemo.data.network

import android.content.Context
import android.content.SharedPreferences
import com.example.androiddemo.utils.AppConstant
import com.example.androiddemo.utils.AppConstant.Companion.PREFERENCE_NAME
import com.google.gson.Gson

class SharedPref(context: Context) : AppConstant {

    private var sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, 0)
    private var editor: SharedPreferences.Editor = sharedPreferences.edit()

    init {
        editor = sharedPreferences?.edit()
    }

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    fun setBoolean(key: String, loggedIn: Boolean) {
        editor.putBoolean(key, loggedIn).apply()
    }

    fun getString(key: String, defaultValue: String = ""): String {
        return sharedPreferences.getString(key, defaultValue)!!
    }

    fun setString(key: String, value: String?) {
        editor.putString(key, value)
        editor.apply()
    }

    fun getInteger(key: String): Int {
        return sharedPreferences.getInt(key, 0)
    }

    fun setInteger(activeJobPosition: Int, key: String) {
        editor.putInt(key, activeJobPosition).apply()
    }

    fun getClassObject(key: String, javaClass: Class<*>): Any? {
        val jsonString = getString(key)
        return Gson().fromJson(jsonString, javaClass)
    }

    fun setClassObject(key: String, classObject: Any) {
        val jsonString = Gson().toJson(classObject)
        setString(key, jsonString)
    }

}