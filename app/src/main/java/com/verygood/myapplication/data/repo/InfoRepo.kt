package com.verygood.myapplication.data.repo

import androidx.lifecycle.LiveData
import com.verygood.myapplication.data.local.DbApi
import com.verygood.myapplication.data.network.NetworkApi
import com.verygood.myapplication.model.City
import com.verygood.myapplication.model.Holder
import com.verygood.myapplication.model.InternalResult
import kotlinx.coroutines.flow.Flow

interface InfoRepo {

    suspend fun fetchUpdates(): InternalResult<Any>

    fun fetchData(): LiveData<List<City>>
}

class InfoRepoImpl constructor(
    private val mDbApi: DbApi,
    private val mNetworkApi: NetworkApi
) : InfoRepo {


    override suspend fun fetchUpdates(): InternalResult<Any> {
        val result = mNetworkApi.fetchUpdates()
        if (result is InternalResult.Success) {
            mDbApi.saveCity(result.data)
        }
        return result
    }

    override fun fetchData(): LiveData<List<City>> {
        return mDbApi.fetchAllCities()
    }

}