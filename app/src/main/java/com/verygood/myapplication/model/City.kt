package com.verygood.myapplication.model

import java.util.UUID

data class City(
    val id: String = UUID.randomUUID().toString(),
    val cityName: String,
    val aqi: Double,
    val timestamp: Long
)
