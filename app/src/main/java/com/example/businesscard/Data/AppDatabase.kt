package com.example.businesscard.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [BusinessCard::class], version = 1)
abstract class AppDatabase: RoomDatabase (){
    abstract fun businessDao(): IBusinessCardDAO
    companion object {
        @Volatile
        private var INSTACE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{
            return INSTACE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "businesscard_db"
                ).build()
                INSTACE = instance
                instance
            }
        }

    }
}