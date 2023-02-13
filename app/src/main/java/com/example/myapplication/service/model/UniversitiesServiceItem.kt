package com.example.myapplication.service.model

data class UniversitiesServiceItem(
    val alpha_two_code: String,
    val country: String,
    val domains: List<String>,
    val name: String,
    val state_province: Any,
    val web_pages: List<String>
)