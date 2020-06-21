package com.example.testparse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.student_list_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private val baseURL = "https://e.mospolytech.ru/?p=portfolio&objsearch="
    private val studentList = mutableListOf<Student>()
    private lateinit var adapter: ParseAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputButton.setOnClickListener {
            inputText.selectAll()
            studentList.clear()
            val textToParse = baseURL.plus(inputText.text.toString().toAnsi())

            adapter = ParseAdapter()
            val llm = LinearLayoutManager(rv.context)
            rv.layoutManager = llm
            rv.adapter = adapter

            GlobalScope.launch {
                getData(textToParse)
            }



        }
    }

    private fun getData(textToParse: String) {
        try {
            val doc = Jsoup.connect(textToParse).get()

            val elements = doc.getElementsByClass("reveal-modal")

            for (i in elements) {
                val studentName = i.select("h4").text().toString()
                val studentGroup = i.select("b").first().text().toString()

                studentList.add(Student(studentName, studentGroup))
            }

            if (studentList.isEmpty()) {
                GlobalScope.launch(Dispatchers.Main) {
                    Toast.makeText(applicationContext, "${inputText.text}: Такого студента не найдено", Toast.LENGTH_SHORT).show()
                }
            }
            else {
                GlobalScope.launch(Dispatchers.Main) {
                    adapter.setData(studentList)
                }
            }


        } catch (e: Exception) {}

    }

    private fun String.toAnsi() = this
                .toLowerCase()
                .map {
                    when (it) {
                        'а' -> "%E0"
                        'б' -> "%E1"
                        'в' -> "%E2"
                        'г' -> "%E3"
                        'д' -> "%E4"
                        'е' -> "%E5"
                        'ё' -> "%B8"
                        'ж' -> "%E6"
                        'з' -> "%E7"
                        'и' -> "%E8"
                        'й' -> "%E9"
                        'к' -> "%EA"
                        'л' -> "%EB"
                        'м' -> "%EC"
                        'н' -> "%ED"
                        'о' -> "%EE"
                        'п' -> "%EF"
                        'р' -> "%F0"
                        'с' -> "%F1"
                        'т' -> "%F2"
                        'у' -> "%F3"
                        'ф' -> "%F4"
                        'х' -> "%F5"
                        'ц' -> "%F6"
                        'ч' -> "%F7"
                        'ш' -> "%F8"
                        'щ' -> "%F9"
                        'ь' -> "%FC"
                        'э' -> "%FD"
                        'ы' -> "%FB"
                        'ю' -> "%FE"
                        'я' -> "%FF"
                        else -> "$it"
                    }
            }
            .joinToString("", "", "")

}