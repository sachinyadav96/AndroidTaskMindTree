package com.mt.android.ui.classes.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mt.android.data.model.MainList
import com.mt.android.data.repository.RetrofitRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val retrofitRepository: RetrofitRepository = RetrofitRepository()

    private var userLiveData: MutableLiveData<MainList> = MutableLiveData()


    fun getUserDataList(): MutableLiveData<MainList> {
        return userLiveData
    }

    fun makeApiCall() {
        viewModelScope.launch(Dispatchers.Main) {

            try {
                val apiResponse = retrofitRepository.getData()
                userLiveData.postValue(apiResponse)
            } catch (e: Exception) {
                e.printStackTrace()
                /**
                 * API is open source its not giving the Response sometime
                 * to prevent from crash and reload data again in case of
                 * api fail to load the data
                 * */
                if (e.toString().contains("HTTP 429 Too Many Requests")) {
                    Log.d("Error", "HTTP 429 Too Many Requests")
                }

                if (e.toString().contains("UnknownHostException: Unable to resolve host")) {
                    Log.d("Error", "UnknownHostException: Unable to resolve host")
                }

            }
        }
    }
}
