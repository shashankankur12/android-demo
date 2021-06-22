package com.example.androiddemo.ui.lumperList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddemo.data.reopsitories.LumperListRepository
import com.example.androiddemo.data.responses.Data
import com.example.androiddemo.utils.ApiExceptions
import com.example.androiddemo.utils.NoInternetException
import com.example.androiddemo.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LumperListViewModel(private val repo: LumperListRepository) : ViewModel() {
    private val lumpersList = MutableLiveData<Resource<List<Data>>>()

    fun getLumperList(): LiveData<Resource<List<Data>>> {
        fetchLumpersList()
        return lumpersList
    }

    private fun fetchLumpersList() {
        lumpersList.postValue(Resource.loading())
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repo.fetchAllLumperData()
                response?.also {
                    if (!it.data.isNullOrEmpty()) {
                        lumpersList.postValue(Resource.success(it.data))
                    } else {
                        lumpersList.postValue(Resource.error("No record found"))
                    }
                } ?: run {
                    lumpersList.postValue(Resource.error("No record found"))
                }
            } catch (e: ApiExceptions) {
                lumpersList.postValue(Resource.error(e.message))
            } catch (e: NoInternetException) {
                lumpersList.postValue(Resource.error(e.message))
            }
        }
    }
}