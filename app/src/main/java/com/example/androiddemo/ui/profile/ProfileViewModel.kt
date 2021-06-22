package com.example.androiddemo.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddemo.data.reopsitories.ProfileRepository
import com.example.androiddemo.data.responses.Data
import com.example.androiddemo.listner.login.ViewModelListener
import com.example.androiddemo.utils.ApiExceptions
import com.example.androiddemo.utils.NoInternetException
import com.example.androiddemo.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: ProfileRepository) : ViewModel() {

    private val profileData= MutableLiveData<Resource<Data>>()

    fun getProfileData(): LiveData<Resource<Data>> {
       fetchProfileData()
        return profileData
    }

    private fun fetchProfileData() {
        profileData.postValue(Resource.loading())
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.fetchProfileData()
                response?.also {
                    if (it.data != null) {
                        profileData.postValue(Resource.success(it.data!!))
                    } else {
                        profileData.postValue(Resource.error("No record found"))
                    }
                } ?: run {
                    profileData.postValue(Resource.error("No record found"))
                }
            } catch (e: ApiExceptions) {
                profileData.postValue(Resource.error(e.message))
            } catch (e: NoInternetException) {
                profileData.postValue(Resource.error(e.message))
            }
        }
    }
}