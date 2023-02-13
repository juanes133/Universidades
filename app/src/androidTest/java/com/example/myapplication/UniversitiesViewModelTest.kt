package com.example.myapplication

import androidx.lifecycle.Observer
import androidx.test.annotation.UiThreadTest
import androidx.test.core.app.ApplicationProvider
import com.example.myapplication.database.UniversitiesDb
import com.example.myapplication.model.UniversityModel
import com.example.myapplication.repository.UniversitiesRepository
import com.example.myapplication.viewmodel.UniversitiesViewModel
import com.example.myapplication.viewmodel.UniversitiesViewModelFactory
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class UniversitiesViewModelTest {

    var finished = false
    var time = 2

    private var universitiesViewModel: UniversitiesViewModel
    private var roomDatabase: UniversitiesDb =
        UniversitiesDb.getDatabase(ApplicationProvider.getApplicationContext())
    private var observerUniversities: Observer<ArrayList<UniversityModel>>? = null
    private var observerDelete: Observer<Boolean>? = null

    init {
        //Given
        universitiesViewModel =
            UniversitiesViewModelFactory(UniversitiesRepository(roomDatabase.universitiesDao())).create(
                UniversitiesViewModel::class.java
            )
    }

    @Before
    fun setUp() {
        finished = false
        time = 2
    }

    @After
    fun tearDown() {
        Thread.sleep(time * 1000L)
        Assert.assertTrue(finished)
    }

    @Test
    @UiThreadTest
    fun getUniversitiesTest() {
        time = 4
        //Given
        universitiesViewModel.deleteData()
        observerDelete = Observer<Boolean> {
            //When
            universitiesViewModel.getUniversities()

            //Then
            observerUniversities = Observer<ArrayList<UniversityModel>> { universities ->
                Assert.assertEquals(206, universities.size)
                universities[0].apply {
                    Assert.assertEquals("Universidad Autónoma de Manizales", name)
                    Assert.assertEquals("Colombia", country)
                    Assert.assertEquals("[http://www.autonoma.edu.co/]", web_pages)
                }
                finished = true
            }
            universitiesViewModel.universitiesList.observeForever(observerUniversities!!)
        }
        universitiesViewModel.delete.observeForever(observerDelete!!)
    }
}