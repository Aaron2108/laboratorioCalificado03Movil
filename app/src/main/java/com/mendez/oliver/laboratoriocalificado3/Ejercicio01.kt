package com.mendez.oliver.laboratoriocalificado3

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mendez.oliver.laboratoriocalificado3.databinding.ActivityEjercicio01Binding
import model.Teacher
import model.TeacherResponse

class Ejercicio01 : AppCompatActivity(), TeacherAdapter.OnItemClickListener {
    private lateinit var binding: ActivityEjercicio01Binding
    private lateinit var teacherAdapter: TeacherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEjercicio01Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        loadTeachers()
    }
    private fun setupRecyclerView() {
        teacherAdapter = TeacherAdapter(emptyList(), this)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@Ejercicio01)
            adapter = teacherAdapter
        }
    }
    private fun loadTeachers() {
        val jsonResponse = """
            {
              "teachers": [
                {
                "name": "Victor Alejandro",
                "last_name": "Saico Justo",
                "phone_number": "+51 925137361",
                "email": "vsaico@tecsup.edu.pe",
                "image_url": "https://raw.githubusercontent.com/victorskatepro/ContactList/master/app/src/main/res/drawable/vsaico.jpeg"
                },
                {
                "name": "Linder Hassinger",
                "last_name": "Saico Justo",
                "phone_number": "+51 925137362",
                "email": "lhassinger@tecsup.edu.pe",
                "image_url": "https://raw.githubusercontent.com/victorskatepro/ContactList/master/app/src/main/res/drawable/lhassinger.jpeg"
                }
              ]
            }
        """.trimIndent()
        val gson = Gson()
        val teacherType = object : TypeToken<TeacherResponse>() {}.type
        val teacherResponse: TeacherResponse = gson.fromJson(jsonResponse, teacherType)
        teacherAdapter = TeacherAdapter(teacherResponse.teachers, this)
        binding.recyclerView.adapter = teacherAdapter
    }
    override fun onItemClick(teacher: Teacher) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:${teacher.phone_number}")
        }
        startActivity(intent)
    }
    override fun onItemLongClick(teacher: Teacher) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:${teacher.email}")
        }
        startActivity(intent)
    }
}