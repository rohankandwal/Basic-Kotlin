package com.itcse.kotlinweatherapp.model.data

import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Query

interface ApiInterface {
    @GET("data/2.5/weather")
    fun getWeatherData(@Query("zip") zip: String,
                       @Query("units") units: String,
                       @Query("appid") appId: String)
            : Call<WeatherData>
}