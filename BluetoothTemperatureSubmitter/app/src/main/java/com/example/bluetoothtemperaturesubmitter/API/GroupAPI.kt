package com.example.bluetoothtemperaturesubmitter.API

import com.example.bluetoothtemperaturesubmitter.DTO.Group
import com.example.bluetoothtemperaturesubmitter.DTO.Groups
import com.example.bluetoothtemperaturesubmitter.DTO.JoinGroup
import com.example.bluetoothtemperaturesubmitter.DTO.User
import retrofit2.Call
import retrofit2.http.*

interface GroupAPI {
    @FormUrlEncoded
    @POST("/groups/")
    fun createGroup(
        @Header("Authorization") token : String,
        @Field("name") name : String
    ) : Call<Groups>

    @POST("/groups/join/")
    fun joinGroup(
        @Header("Authorization") token : String,
        @Body group : Group
    ) : Call<JoinGroup>

    @GET("/groups/mygroup/")
    fun getMyGroup(
        @Header("Authorization") token : String
    ) : Call<List<Groups>>
    @GET("/groups/")
    fun getGroup(
        @Header("Authorization") token : String
    ) : Call<List<Groups>>

    @GET("/groups/{group_id}/")
    fun getGroupMember(
        @Header("Authorization") token : String,
        @Path("group_id") group_id : String
    ) : Call<List<User>>
}