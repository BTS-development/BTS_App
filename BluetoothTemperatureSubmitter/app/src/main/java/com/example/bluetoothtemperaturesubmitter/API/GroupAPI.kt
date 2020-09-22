package com.example.bluetoothtemperaturesubmitter.API

import com.example.bluetoothtemperaturesubmitter.DTO.Groups
import com.example.bluetoothtemperaturesubmitter.DTO.JoinGroup
import retrofit2.Call
import retrofit2.http.*

interface GroupAPI {
    @POST("/groups")
    fun createGroup(
        @Header("Authorization") token : String,
        @Field("name") name : String
    ) : Call<Groups>
    @POST("/groups/join")
    fun joinGroup(
        @Header("Authorization") token : String,
        @Field("code") code : String
    ) : Call<JoinGroup>
    @GET("/groups/mygroup")
    fun getMygroup(
        @Header("Authorization") token : String
    ) : Call<ArrayList<Groups>>
}