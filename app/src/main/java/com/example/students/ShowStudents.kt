package com.example.students

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import com.example.students.Data.StudentsDb
import com.example.students.Data.StudentsEntity
import kotlinx.android.synthetic.main.activity_show_students.*

class ShowStudents : AppCompatActivity() {

    private var listStudents:ArrayList<StudentsEntity>? = null

    val studentsDb = StudentsDb(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_students)

       listStudents = studentsDb.studentsGetAll()
        Log.i("BossListCount","${listStudents!!.size}")

        ltvEstudiantes.adapter = customerAdapter(this,listStudents!!)

    }
}


class customerAdapter (context: Context, private val list: ArrayList<StudentsEntity>):
    ArrayAdapter<String>(context, android.R.layout.simple_list_item_1) {
    override fun getCount(): Int = list.size
    override fun getItemId(position: Int): Long = position.toLong()
    override fun getItem(position: Int): String = "Test Cell"

    @SuppressLint("View Holder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup):View {
        val layoutInflater = LayoutInflater.from(context)
        val row = layoutInflater.inflate(R.layout.row_main,parent,false)
        val nameTextView = row.findViewById<TextView>(R.id.name_textView)
        nameTextView.text = list[position].id.toString() + " " + list[position].nombre
        return row
    }
}