package com.example.bluetoothtemperaturesubmitter.retrofit

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserAPI {
    @POST("/users/account/signup")
    fun signUp(
        @Body username : String,
        @Body email : String,
        @Body password : String,
        @Body password2 : String
    ) : Call<Boolean>
    @POST("/users/account/login")
    fun login() : Call<Boolean>

}