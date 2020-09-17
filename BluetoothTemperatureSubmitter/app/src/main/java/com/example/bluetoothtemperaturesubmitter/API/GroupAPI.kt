package com.example.bluetoothtemperaturesubmitter.API

import com.example.bluetoothtemperaturesubmitter.DTO.Groups
import com.example.bluetoothtemperaturesubmitter.DTO.JoinGroup
import retrofit2.Call
import retrofit2.http.*

interface GroupAPI {
    @POST("/groups")
    fun createGroup(
        @Field("name") name : String
    ) : Call<Groups>
    @POST("/groups/join")
    fun joinGroup(
        @Field("code") code : String
    ) : Call<JoinGroup>
    @GET("/groups/mygroup")
    fun getMygroup() : Call<ArrayList<Groups>>
}