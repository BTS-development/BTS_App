package com.example.bluetoothtemperaturesubmitter.API

import com.example.bluetoothtemperaturesubmitter.DTO.Temperature
import retrofit2.Call
import retrofit2.http.*

interface TemperatureAPI {
    @FormUrlEncoded
    @POST("/temperatures")
    fun postTemp(
        @Header("Authorization") token : String,
        @Field("value") temperature : Double
    ) : Call<Temperature>
    @GET("/temperatures/{user_id}")
    fun getTemp(
        @Header("Authorization") token : String,
        @Path("user_id") user_id : String
    ) : Call<Temperature>
    @GET("/temperatures/my/")
    fun getMyTemp(
        @Header("Authorization") token : String
    ) : Call<ArrayList<Temperature>>
    @GET("/temperatures/group/{group_id}")
    fun getGroupTemp(
        @Header("Authorization") token : String
    ) : Call<ArrayList<Temperature>>
}