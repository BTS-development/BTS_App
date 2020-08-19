package com.example.bluetoothtemperaturesubmitter.retrofit

import retrofit2.Call
import retrofit2.http.*

interface GroupAPI {
    @POST("/groups")
    fun createGroup(
        @Field("name") name : String
    ) : Call<GroupData>

    @POST("/group/join")
    fun joinGroup(
        @Field ("code") code : String
    ) : Call<Boolean>

    @GET("/groups/{group_id}")
    fun getGroupInfo(
        @Path("group_id") group_id : String
    ) : Call<GroupData>


}