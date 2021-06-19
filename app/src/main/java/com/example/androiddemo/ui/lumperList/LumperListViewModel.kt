package com.example.androiddemo.ui.lumperList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddemo.data.reopsitories.LumperListRepository
import com.example.androiddemo.data.responses.Data
import com.example.androiddemo.listner.login.ViewModelListener
import com.example.androiddemo.utils.ApiExceptions
import com.example.androiddemo.utils.NoInternetException
import kotlinx.coroutines.launch

class LumperListViewModel(private val repo: LumperListRepository) : ViewModel() {

    internal val lumpersList: MutableLiveData<List<Data>> by lazy {
        MutableLiveData<List<Data>>().also {
            fetchLumpersList()
        }
    }
    var viewModelListener: ViewModelListener? = null

//    val lumperList by lazyDeferred { repo.getLumperList() }

    fun getLumperList():LiveData<List<Data>>{
        return lumpersList
    }
    fun fetchLumpersList() {
        viewModelListener?.onStarted()
        viewModelScope.launch {
            try {
                val response = repo.fetchAllLumperData()
                response?.also {
                    if (!it.data.isNullOrEmpty()) {
                        viewModelListener?.onSuccess("")
                        lumpersList.postValue(it.data)
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