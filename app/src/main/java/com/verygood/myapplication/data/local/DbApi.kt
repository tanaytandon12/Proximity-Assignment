package com.verygood.myapplication.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.google.android.material.transition.Hold
import com.verygood.myapplication.model.City
import com.verygood.myapplication.model.Holder
import com.verygood.myapplication.model.InternalResult
import kotlinx.coroutines.flow.Flow

interface DbApi {

     fun fetchAllCities(): LiveData<List<City>>

    suspend fun saveCity(cities: List<City>): InternalResult<Any>
}

object DbApiImpl : DbApi {
    override  fun fetchAllCities(): LiveData<List<City>> {
        return liveData {
            emit(Holder.data)
        }
    }

    override suspend fun saveCity(cities: List<City>): InternalResult<Any> {
        Holder.data = cities
        return InternalResult.Success(Any())
    }

}