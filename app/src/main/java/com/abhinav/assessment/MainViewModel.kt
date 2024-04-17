package com.abhinav.assessment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhinav.assessment.api.Api
import com.abhinav.assessment.model.Data
import com.abhinav.assessment.model.FoodInfoResponse
import com.abhinav.assessment.model.emptyData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "FoodInfoFragment"

class MainViewModel(private val api: Api) : ViewModel() {

    private val _flow = MutableStateFlow(emptyData())
    val flow : StateFlow<Data> get() = _flow

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val response = api.getFoodInfo()
            if (response.isSuccessful) {
                if (response.body() != null) {
                    _flow.emit(response.body()!!.data)
                }
            }
        }
    }
}