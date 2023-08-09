MVVM design pattern, CLEAN data, domain and presentation layers
SOLID design programming principles

Architecture 

data -> model + data package 
City 
InfoRepo 
InfoRepoImpl
DbApi 
DbApiImpl
NetworkApi
NetworkApiImpl

domain -> CityUseCase CityUsecaseImpl

presentation -> CityViewModel, CityFragment, CityAdapter, CityViewHolder


Tech Stack

Kotlin
Jetpack LiveData 
Jetpack ViewModel

Requirements to actually run this project:
1) The implementation of the database layer, at the moment it just uses in memory storage, use Room to persist the data
2) Add implementation to constantly listen to the socket events
3) Add UI layer implementation, consume the data from the view model
4) Add Dependency Injection to insert the view model dependency into the fragment class
5) Add the INTERNET permission in the Android Manifest
6) Once the implementations are there add Unit tests 

