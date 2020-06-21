package com.example.testparse

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ParseAdapter : RecyclerView.Adapter<ViewHolder>() {

    val studentList = mutableListOf<Student>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.parse_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = studentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(studentList[position])
    }


    fun setData(list: MutableList<Student>) {
        this.studentList.clear()
        this.studentList.addAll(list)
        notifyDataSetChanged()
    }
}