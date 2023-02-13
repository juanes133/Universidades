package com.example.myapplication

import android.app.Application
import com.example.myapplication.database.UniversitiesDb
import com.example.myapplication.repository.UniversitiesRepository

class UniversitiesApplication : Application() {

    private val roomDatabase by lazy { UniversitiesDb.getDatabase(this) }
    val universitiesRepository by lazy { UniversitiesRepository(roomDatabase.universitiesDao()) }
}