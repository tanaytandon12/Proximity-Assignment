package com.verygood.myapplication.city

import androidx.lifecycle.*
import androidx.viewbinding.BuildConfig
import com.verygood.myapplication.model.City
import com.verygood.myapplication.model.InternalResult
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

abstract class CityViewModel : ViewModel() {

    abstract fun data(): LiveData<CityUIInfo>
}

class CityViewModelImpl constructor(
    private val mUsecase: CityUsecase,
    private val mIODisptacher: CoroutineContext = Dispatchers.IO
) : CityViewModel() {

    private val mExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        // TODO stacktrace should only print in the debug mode
        throwable.printStackTrace()
    }

    init {
        viewModelScope.launch(mExceptionHandler + mIODisptacher) {
            val result = mUsecase.fetchInfo()
            if (result is CityNetworkResult.Error) {
                mUILiveData.postValue(CityUIInfo.Error(result.msg))
            }
        }
    }

    private val mUILiveData: MutableLiveData<CityUIInfo> by lazy {
        MutableLiveData<CityUIInfo>(CityUIInfo.Loading)
    }


    override fun data(): LiveData<CityUIInfo> {
        return liveData {
            val mediator = MediatorLiveData<CityUIInfo>()
            emitSource(mediator)
            mediator.addSource(mUILiveData) {
                mediator.postValue(it)
            }
            mediator.addSource(mUILiveData) {
                mediator.postValue(it)
            }
        }
    }

}