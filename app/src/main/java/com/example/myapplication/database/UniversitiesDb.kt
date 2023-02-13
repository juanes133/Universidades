package com.example.myapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.database.dao.UniversitiesDao
import com.example.myapplication.database.entities.UniversitiesEntity

@Database(entities = [UniversitiesEntity::class], version = 1)
abstract class UniversitiesDb : RoomDatabase() {

    abstract fun universitiesDao(): UniversitiesDao

    companion object {
        @Volatile
        private var INSTANCE: UniversitiesDb? = null

        fun getDatabase(context: Context): UniversitiesDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UniversitiesDb::class.java,
                    "universities_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}