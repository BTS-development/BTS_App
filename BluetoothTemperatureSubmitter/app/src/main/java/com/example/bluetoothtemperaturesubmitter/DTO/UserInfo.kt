package com.example.bluetoothtemperaturesubmitter.DTO

data class UserInfo(
    val id : Int,
    val password : String,
    val last_login : String,
    val username : String,
    val email : String,
    val is_active : Boolean,
    val is_admin : Boolean
)