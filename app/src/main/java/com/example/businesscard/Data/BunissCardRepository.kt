package com.example.businesscard.Data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class BunissCardRepository(private val dao: IBusinessCardDAO) {
    fun insert(businessCard: BusinessCard) = runBlocking {
        launch (Dispatchers.IO){
            dao.insert(businessCard)
        }
    }
    fun getAll()= dao.getAll()
}