package com.example.bluetoothtemperaturesubmitter.API

import com.example.bluetoothtemperaturesubmitter.DTO.Temperature
import retrofit2.Call
import retrofit2.http.*

interface TemperatureAPI {
    @POST("/temperatures")
    fun postTemp(
        @Field("value") temperature : Double
    ) : Call<Temperature>
    @GET("/temperatures/{user_id}")
    fun getTemp(
        @Path("user_id") user_id : String
    ) : Call<Temperature>
    @GET("/temperatures/my/")
    fun getMyTemp() : Call<ArrayList<Temperature>>
    @GET("/temperatures/group/{group_id}")
    fun getGroupTemp() : Call<ArrayList<Temperature>>
}