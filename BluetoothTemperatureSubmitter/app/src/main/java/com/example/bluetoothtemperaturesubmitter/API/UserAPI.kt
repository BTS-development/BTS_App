package com.example.bluetoothtemperaturesubmitter.API

import com.example.bluetoothtemperaturesubmitter.DTO.Login
import com.example.bluetoothtemperaturesubmitter.DTO.Signup
import com.example.bluetoothtemperaturesubmitter.DTO.UserInfo
import retrofit2.Call
import retrofit2.http.*

interface UserAPI {
    @POST("/users/account/signup")
    fun signUp(
        @Field("username") username : String,
        @Field("email") email : String,
        @Field("password1") password1 : String,
        @Field("password2") password2 : String
    ) : Call<Signup>
    @POST("/users/account/login")
    fun signIn(
        @Field("username") username : String,
        @Field("email") email : String,
        @Field("password") password : String
    ) : Call<Login>
    @GET("/users/account/{userid}")
    fun getUser(
        @Path("userid") userid : String
    ) : Call<UserInfo>
}