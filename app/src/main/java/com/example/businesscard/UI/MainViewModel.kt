package com.example.businesscard.UI

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.businesscard.Data.BunissCardRepository
import com.example.businesscard.Data.BusinessCard

class MainViewModel(private val businessCardRepository: BunissCardRepository):ViewModel() {
    fun insert(businessCard: BusinessCard){
        businessCardRepository.insert(businessCard)

    }
    fun getAll(): LiveData<List<BusinessCard>>{
        return businessCardRepository.getAll()
    }

}
class MainViewModelFactory(private val repository: BunissCardRepository):
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(MainViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
    }

}