package com.verygood.myapplication.city

import com.verygood.myapplication.model.City

sealed class CityUIInfo {

    sealed class Success:CityUIInfo() {
        object NoData : Success()
        data class Data(val items: List<CityListItem>) : Success()
    }

    object Loading : CityUIInfo()

    data class Error(val error: String) : CityUIInfo()
}

sealed class CityNetworkResult {
    object Success:CityNetworkResult()
    data class Error(val msg:String):CityNetworkResult()
}

sealed class CityListItem {
    data class Item(val city: City) : CityListItem()
}