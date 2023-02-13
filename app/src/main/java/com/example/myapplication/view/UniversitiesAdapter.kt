package com.example.myapplication.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.model.UniversityModel

class UniversitiesAdapter(
    private val universityList: ArrayList<UniversityModel>,
    private val onClickListener: (UniversityModel) -> Unit,
) : RecyclerView.Adapter<UniversitiesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UniversitiesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return UniversitiesViewHolder(
            layoutInflater.inflate(R.layout.item_university, parent, false)
        )
    }

    override fun onBindViewHolder(holder: UniversitiesViewHolder, position: Int) {
        holder.render(universityList[position], onClickListener)
    }

    override fun getItemCount(): Int = universityList.size
}