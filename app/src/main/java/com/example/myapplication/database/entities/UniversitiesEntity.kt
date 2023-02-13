package com.example.myapplication.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "universities_table")
class UniversitiesEntity(
    @PrimaryKey
    val country: String,
    val name: String,
    val web_pages: String,
)