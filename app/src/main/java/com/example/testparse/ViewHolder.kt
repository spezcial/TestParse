package com.example.testparse

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.parse_item.view.*

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val studentName: TextView = itemView.name_info
    private val groupName: TextView = itemView.group_info


    fun bind(student: Student) {
        studentName.text = student.studentName
        groupName.text = student.studentGroup
    }

}