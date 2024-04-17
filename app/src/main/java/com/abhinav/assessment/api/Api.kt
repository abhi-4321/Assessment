package com.abhinav.assessment.api

import com.abhinav.assessment.model.Data
import com.abhinav.assessment.model.FoodInfoResponse
import com.abhinav.assessment.model.HompageResponse
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("/food_info")
    suspend fun getFoodInfo() : Response<FoodInfoResponse>

    @GET("/homepage_v2")
    fun getHomeInfo() : Response<HompageResponse>
}