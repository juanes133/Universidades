package com.example.myapplication.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.model.UniversityModel
import com.example.myapplication.databinding.ItemUniversityBinding

class UniversitiesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemUniversityBinding.bind(view)

    fun render(
        universityModel: UniversityModel,
        onClickListener: (UniversityModel) -> Unit,
    ) {
        binding.name.text = universityModel.name
        binding.country.text = universityModel.country
        binding.webPages.text = universityModel.web_pages
            .replace("[", "")
            .replace("]", "")
        binding.webPages.setOnClickListener {
            onClickListener(universityModel)
        }
    }
}