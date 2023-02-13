package com.example.myapplication.viewmodel

import androidx.lifecycle.*
import com.example.myapplication.database.entities.UniversitiesEntity
import com.example.myapplication.model.UniversityModel
import com.example.myapplication.repository.UniversitiesRepository
import kotlinx.coroutines.launch

class UniversitiesViewModel(private val universitiesRepository: UniversitiesRepository) :
    ViewModel() {

    private val mutableUniversitiesList = MutableLiveData<ArrayList<UniversityModel>>()
    val universitiesList: LiveData<ArrayList<UniversityModel>> get() = mutableUniversitiesList

    private val mutableDelete = MutableLiveData<Boolean>()
    val delete: LiveData<Boolean> get() = mutableDelete

    private val mutableUniversitiesError = MutableLiveData<Exception>()
    val universitiesError: LiveData<Exception> get() = mutableUniversitiesError


    fun getUniversities() {
        viewModelScope.launch {
            universitiesRepository.getUniversities({ list ->
                val result = ArrayList<UniversityModel>()
                list.forEach {
                    result.add(
                        UniversityModel(
                            it.name,
                            it.country,
                            it.web_pages
                        )
                    )
                }
                mutableUniversitiesList.value = result
            }, {
                mutableUniversitiesError.value = it
            })
        }
    }

    fun deleteData() {
        viewModelScope.launch {
            universitiesRepository.deleteAll()
            mutableDelete.value = true
        }
    }
}

class UniversitiesViewModelFactory(
    private val UniversitiesRepository: UniversitiesRepository,
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UniversitiesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UniversitiesViewModel(UniversitiesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}