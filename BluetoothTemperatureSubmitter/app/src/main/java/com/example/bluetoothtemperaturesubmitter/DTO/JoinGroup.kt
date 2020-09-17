package com.example.bluetoothtemperaturesubmitter.DTO

data class JoinGroup(
    val id : Int,
    val joined_at : String,
    val group : Int,
    val member : Int
)