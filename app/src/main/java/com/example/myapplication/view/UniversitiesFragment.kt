package com.example.myapplication.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.UniversitiesApplication
import com.example.myapplication.databinding.FragmentUniversitiesBinding
import com.example.myapplication.model.UniversityModel
import com.example.myapplication.viewmodel.UniversitiesViewModel
import com.example.myapplication.viewmodel.UniversitiesViewModelFactory


class UniversitiesFragment : Fragment() {

    private lateinit var binding: FragmentUniversitiesBinding
    private val universitiesViewModel: UniversitiesViewModel by viewModels {
        UniversitiesViewModelFactory(
            (activity?.application as UniversitiesApplication).universitiesRepository,
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentUniversitiesBinding.inflate(inflater, container, false)
        universitiesViewModel.universitiesList.observe(viewLifecycleOwner) {
            showUniversityList(it)
        }
        universitiesViewModel.universitiesError.observe(viewLifecycleOwner) {
            binding.universitiesContainer.isVisible = false
            binding.fallbackContainer.isVisible = true
            binding.loading.isVisible = false
        }
        binding.btnRetry.setOnClickListener {
            getUniversities()
        }
        getUniversities()
        binding.loading.isVisible = true
        binding.fallbackContainer.isVisible = true
        return (binding.root)
    }

    private fun getUniversities() {
        binding.universitiesContainer.isVisible = false
        binding.fallbackContainer.isVisible = false
        binding.loading.isVisible = true
        universitiesViewModel.getUniversities()
    }

    private fun showUniversityList(universityList: ArrayList<UniversityModel>) {
        initUniversityRecyclerView(universityList)
        binding.loading.isVisible = false
        binding.universitiesContainer.isVisible = true
        binding.fallbackContainer.isVisible = false
        binding.textListEmpty.isVisible = false
    }

    private fun initUniversityRecyclerView(list: ArrayList<UniversityModel>) {
        binding.recyclerUniversity.layoutManager = LinearLayoutManager(context)
        binding.recyclerUniversity.adapter = UniversitiesAdapter(list) {
            val browserIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(it.web_pages.replace("[", "").replace("]", ""))
            )
            startActivity(browserIntent)
        }
    }
}