package com.example.bluetoothtemperaturesubmitter.API

import com.example.bluetoothtemperaturesubmitter.R
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {

    companion object{

        var retrofit = Retrofit.Builder()
            .baseUrl(R.string.BASE_URL.toString())
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
}