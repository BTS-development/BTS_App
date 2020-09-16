package com.example.bluetoothtemperaturesubmitter.DTO

data class Temperature(
    val id : Int,
    val value : String,
    val created_at : String,
    val owner : Int
)