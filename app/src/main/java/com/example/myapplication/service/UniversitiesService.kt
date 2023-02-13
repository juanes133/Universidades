package com.example.myapplication.service

import com.example.myapplication.service.model.UniversitiesServiceItem
import retrofit2.http.GET

interface UniversitiesService {

    @GET("search?country=Colombia")
    suspend fun listUniversities(
    ): ArrayList<UniversitiesServiceItem>
}