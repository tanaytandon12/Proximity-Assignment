package com.verygood.myapplication.data.network

import com.verygood.myapplication.model.City
import com.verygood.myapplication.model.InternalResult

interface NetworkApi {

    suspend fun fetchUpdates(): InternalResult<List<City>>
}

object NetworkApiImpl : NetworkApi {
    override suspend fun fetchUpdates(): InternalResult<List<City>> {
        val count = (Math.random() * 100).toInt() % 10
        val list = mutableListOf<City>()
        for (index in 0..count) {
            list.add(randomCityObj())
        }

        return if ((Math.random() * 100).toInt() % 100 != 99) {
            InternalResult.Success(list)
        } else {
            InternalResult.Error("random error occurred")
        }
    }

    private fun randomCityObj(): City =
        City(
            cityName = "random_${(Math.random() * 100).toInt()}",
            timestamp = System.currentTimeMillis(),
            aqi = Math.random() * 500
        )

}