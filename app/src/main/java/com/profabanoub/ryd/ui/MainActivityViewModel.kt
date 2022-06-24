package com.profabanoub.ryd.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.profabanoub.ryd.BuildConfig
import com.profabanoub.ryd.data.remote.DataAPI
import com.profabanoub.ryd.model.Video
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivityViewModel : ViewModel() {

    var queryStateFlow = MutableStateFlow(Video())

    fun getVideoData(id: String) = viewModelScope.launch {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val dataAPI = retrofit.create(DataAPI::class.java)

        try {
            queryStateFlow.value = dataAPI.getVideoData(id).body()!!
        } catch (e: NullPointerException) {
        }
    }
}