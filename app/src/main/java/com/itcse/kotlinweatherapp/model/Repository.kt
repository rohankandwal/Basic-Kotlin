package com.itcse.kotlinweatherapp.model

import com.itcse.kotlinweatherapp.model.data.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repository {
    private var mRetrofit: Retrofit? = null;

    companion object {
        private val BASE_URL: String = "http://api.openweathermap.org/";
    }

    fun getApiInterface(): ApiInterface {
        if (mRetrofit == null) {
            mRetrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL).build();
        }
        return mRetrofit!!.create(ApiInterface::class.java)
    }
}
