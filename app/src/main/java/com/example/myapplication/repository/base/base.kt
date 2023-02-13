package com.example.myapplication.repository.base

import com.example.myapplication.service.UniversitiesService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class BaseRepository {

    private val retrofit = Retrofit.Builder()
        .baseUrl("http://universities.hipolabs.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: UniversitiesService = retrofit.create(UniversitiesService::class.java)
}