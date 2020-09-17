package com.example.bluetoothtemperaturesubmitter.DTO

data class Temperature(
    val id : Int,
    val value : Double,
    val created_at : String,
    val owner : Int
)

