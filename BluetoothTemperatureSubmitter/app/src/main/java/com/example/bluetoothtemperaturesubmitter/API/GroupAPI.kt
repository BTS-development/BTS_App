package com.example.bluetoothtemperaturesubmitter.API

import com.example.bluetoothtemperaturesubmitter.DTO.Groups
import retrofit2.Call
import retrofit2.http.*

interface GroupAPI {
    @POST("/groups")
    fun createGroup(
        @Field("name") name : String
    ) : Call<Groups>
}