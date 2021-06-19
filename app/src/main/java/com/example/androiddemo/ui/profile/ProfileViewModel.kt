package com.example.androiddemo.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddemo.data.reopsitories.ProfileRepository
import com.example.androiddemo.data.responses.Data
import com.example.androiddemo.data.responses.LeadProfileAPIResponse
import com.example.androiddemo.listner.login.ViewModelListener
import com.example.androiddemo.utils.ApiExceptions
import com.example.androiddemo.utils.NoInternetException
import com.example.androiddemo.utils.lazyDeferred
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: ProfileRepository) : ViewModel() {

    internal val profileData: MutableLiveData<Data> by lazy {
        MutableLiveData<Data>().also {
            fetchProfileData()
        }
    }
    var viewModelListener: ViewModelListener? = null

//    val lumperList by lazyDeferred {
//        repository.getLumperList()
//    }

    fun getProfileData(): LiveData<Data>{
        return profileData
    }

    fun fetchProfileData() {
        viewModelListener?.onStarted()
        viewModelScope.launch {
            try {
                val response = repository.fetchProfileData()
                response?.also {
                    if (it.data != null) {
                        viewModelListener?.onSuccess("")
                        profileData.postValue(it.data)
                    } else {
                        viewModelListener?.onFailure("No record found")
                    }
                } ?: run {
                    viewModelListener?.onFailure("No record found")
                }
            } catch (e: ApiExceptions) {
                viewModelListener?.onFailure(e.message)
            } catch (e: NoInternetException) {
                viewModelListener?.onFailure(e.message)
            }
        }
    }
}