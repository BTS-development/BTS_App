package com.example.bluetoothtemperaturesubmitter.API

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitHelper{

        private var okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(20, TimeUnit.SECONDS)
        .writeTimeout(20, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()

        private var retrofit = Retrofit.Builder()
            .baseUrl("http://13.124.153.76/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        fun getUserAPI() : UserAPI{
            return retrofit.create(UserAPI::class.java)
        }
        fun getGroupAPI() : GroupAPI{
            return retrofit.create(GroupAPI::class.java)
        }
        fun getTemperatureAPI() : TemperatureAPI{
            return retrofit.create(TemperatureAPI::class.java)
        }

}