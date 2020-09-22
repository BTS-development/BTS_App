package com.example.bluetoothtemperaturesubmitter.API

import com.example.bluetoothtemperaturesubmitter.DTO.Groups
import com.example.bluetoothtemperaturesubmitter.DTO.JoinGroup
import retrofit2.Call
import retrofit2.http.*

interface GroupAPI {
    @FormUrlEncoded
    @POST("/groups/")
    fun createGroup(
        @Header("Authorization") token : String,
        @Field("name") name : String
    ) : Call<Groups>
    @FormUrlEncoded
    @POST("/groups/join")
    fun joinGroup(
        @Header("Authorization") token : String,
        @Field("code") code : String
    ) : Call<JoinGroup>
    @GET("/groups/mygroup")
    fun getMyGroup(
        @Header("Authorization") token : String
    ) : Call<List<Groups>>
    @GET("/groups/")
    fun getGroup(
        @Header("Authorization") token : String
    ) : Call<List<Groups>>
}