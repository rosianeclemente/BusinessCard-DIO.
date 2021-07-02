package com.example.businesscard

import android.app.Application
import com.example.businesscard.Data.AppDatabase
import com.example.businesscard.Data.BunissCardRepository

class App : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { BunissCardRepository(database.businessDao())}


}