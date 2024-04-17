package com.abhinav.assessment.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.locks.Lock

const val BASE_URL = "http://52.25.229.242:8000/"

class RetrofitClient {
    companion object {
        private var instance : Api? = null

        @Synchronized
        fun getInstance(): Api {
            if (instance == null)
                instance = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()
                    .create(Api::class.java)
            return instance as Api
        }
    }
}