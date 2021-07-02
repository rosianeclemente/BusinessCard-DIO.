package com.example.businesscard.Data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface IBusinessCardDAO {

    @Query("SELECT * FROM BusinessCard" )
    fun getAll():LiveData<List<BusinessCard>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(businesscard: BusinessCard)
}