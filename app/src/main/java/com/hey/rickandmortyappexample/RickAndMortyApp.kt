package com.hey.rickandmortyappexample

import android.app.Application
import com.hey.rickandmortyappexample.data.db.AppDataBase
import com.hey.rickandmortyappexample.data.db.AppDataBase.Companion.getDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class RickAndMortyApp : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())
    val database: AppDataBase by lazy { getDatabase(this, applicationScope) }


    override fun onCreate() {
        super.onCreate()
    }

}