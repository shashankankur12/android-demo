package com.example.androiddemo.data.reopsitories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.androiddemo.data.network.ApiInterFace
import com.example.androiddemo.data.network.SafeApiRequest
import com.example.androiddemo.data.network.SharedPref
import com.example.androiddemo.data.responses.Data
import com.example.androiddemo.data.responses.EmployeeData
import com.example.androiddemo.utils.AppConstant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LumperListRepository(private val api :ApiInterFace,private val pref: SharedPref) :
    SafeApiRequest() {

    private val lumperList= MutableLiveData<List<Data>>()

    suspend fun getLumperList ():LiveData<List<Data>>{
        return withContext(Dispatchers.IO){
            fetchLumperList()
            lumperList
        }
    }

    private suspend fun fetchLumperList(){
        val response= apiRequest {
            api.getAllLumpersData("2")
        }
        val allList = ArrayList<Data>()
        response?.data?.let {
            it?.let { it1 -> allList.addAll(it1) }
        }
        lumperList.postValue(allList)
    }
}