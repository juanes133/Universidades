package com.example.myapplication.repository

import com.example.myapplication.database.dao.UniversitiesDao
import com.example.myapplication.database.entities.UniversitiesEntity
import com.example.myapplication.repository.base.BaseRepository

class UniversitiesRepository(private val universitiesDao: UniversitiesDao) : BaseRepository(){

    suspend fun getUniversities(
        onSuccess: (ArrayList<UniversitiesEntity>) -> Unit,
        onFailure: (Exception) -> Unit,
    ) {
        try {
            val result = ArrayList<UniversitiesEntity>()
            service.listUniversities().forEach {
                result.add(
                    UniversitiesEntity(
                        it.name,
                        it.country,
                        it.web_pages.toString()
                    )
                )
            }
            onSuccess(result)
        } catch (e: Exception) {
            onFailure(e)
        }
    }

    suspend fun deleteAll() {
        universitiesDao.deleteAll()
    }
}
