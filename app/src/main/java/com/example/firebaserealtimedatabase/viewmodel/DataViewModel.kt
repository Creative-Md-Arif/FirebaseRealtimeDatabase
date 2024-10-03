package com.example.firebaserealtimedatabase.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firebaserealtimedatabase.model.Data
import com.example.firebaserealtimedatabase.repository.DataRepository

class DataViewModel : ViewModel() {
    private val dataRepository = DataRepository()
    private var _dataList:MutableLiveData<List<Data>>  =dataRepository.fetchData()
    val dataList:LiveData<List<Data>> = _dataList

    private val _error = MutableLiveData<String>()

    val error: MutableLiveData<String> get() = _error

    fun addData(data: Data , onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        dataRepository.addData(data)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener {
                onFailure(it)
            }
    }

    fun updateData(data: Data , onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        dataRepository.updateData(data)
    }

    fun handleError(error: Exception) {
        _error.value = error.message

    }

}