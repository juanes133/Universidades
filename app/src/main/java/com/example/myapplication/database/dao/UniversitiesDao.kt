package com.example.myapplication.database.dao

import androidx.room.*
import com.example.myapplication.database.entities.UniversitiesEntity

@Dao
interface UniversitiesDao {

    @Query("SELECT * FROM universities_table")
    suspend fun getAll(): List<UniversitiesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(universities: List<UniversitiesEntity>)

    @Delete
    suspend fun delete(university: UniversitiesEntity)

    @Query("DELETE FROM universities_table")
    suspend fun deleteAll()

}
