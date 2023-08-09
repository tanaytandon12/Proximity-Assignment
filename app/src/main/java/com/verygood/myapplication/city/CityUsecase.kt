package com.verygood.myapplication.city

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.verygood.myapplication.data.repo.InfoRepo
import com.verygood.myapplication.model.InternalResult
import kotlinx.coroutines.flow.Flow

interface CityUsecase {

    suspend fun fetchInfo(): CityNetworkResult

    fun data(): LiveData<CityUIInfo>
}

class CityUsecaseImpl constructor(
    private val mInfoRepo: InfoRepo
) : CityUsecase {
    override suspend fun fetchInfo(): CityNetworkResult {
        return when (mInfoRepo.fetchUpdates()) {
            is InternalResult.Success -> {
                CityNetworkResult.Success
            }
            else -> {
                CityNetworkResult.Error("some error msg")
            }
        }
    }

    override fun data(): LiveData<CityUIInfo> {
        return mInfoRepo.fetchData().map {
            val data: CityUIInfo.Success = if (it.isNotEmpty()) {
                CityUIInfo.Success.Data(it.map { city ->
                    CityListItem.Item(city)
                })
            } else {
                CityUIInfo.Success.NoData
            }
            data
        }
    }

}