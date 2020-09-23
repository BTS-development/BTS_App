package com.example.bluetoothtemperaturesubmitter.API

import android.content.Context
import com.example.bluetoothtemperaturesubmitter.R
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper{



        private var retrofit = Retrofit.Builder()
            .baseUrl("http://13.124.153.76/")
            .addConverterFactory(GsonConverterFactory.create())
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