package com.example.students

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.students.Data.StudentsDb
import com.example.students.Data.StudentsEntity
import kotlinx.android.synthetic.main.activity_show_students.*

class ShowStudents : AppCompatActivity() {

    private var listStudents:ArrayList<StudentsEntity>? = null
    private val studentsDb = StudentsDb(this)
    private var type: String? = null
    private var student = StudentsEntity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_students)

        type = intent.getStringExtra("type")

        //  listStudents = studentsDb.studentsGetAll()
        // ltvEstudiantes.adapter = customerAdapter(this,listStudents!!)

        val myAdapter = ArrayAdapter<String>(this@ShowStudents,android.R.layout.simple_list_item_1,studentsDb.studentsGetAll())
        ltvEstudiantes.adapter = myAdapter

        ltvEstudiantes.setOnItemClickListener { adapterView: AdapterView<*>, view, position, id ->
            //val student = listStudents!![position]

            val item = adapterView.getItemAtPosition(position).toString()
            val splitItem = item.split(" ") //la cadena
            student.id = splitItem[0].toInt()

            if (type == "edit") {
                val intentEdit = Intent(this,EditStudents::class.java)
                intentEdit.putExtra("ID",student.id)
                startActivity(intentEdit)
            }else{
                val intentDetail = Intent(this, DetailStudents::class.java)
                intentDetail.putExtra("ID",student.id)
                /*intentDetail.putExtra("Name", student.nombre)
                intentDetail.putExtra("LastName",student.apellido)
                intentDetail.putExtra("Email",student.correo)
                intentDetail.putExtra("Phone",student.telefono)
                intentDetail.putExtra("Date",student.birthday)
                intentDetail.putExtra("Gender",student.gender)*/
                startActivity(intentDetail)
            }
        }
        onRestart()
    }
}

/*class customerAdapter (context: Context, private val list: ArrayList<StudentsEntity>):
    ArrayAdapter<String>(context, android.R.layout.simple_list_item_1) {
    override fun getCount(): Int = list.size
    override fun getItemId(position: Int): Long = position.toLong()
    override fun getItem(position: Int): String = "Test Cell"

    @SuppressLint("View Holder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup):View {
        val layoutInflater = LayoutInflater.from(context)
        val row = layoutInflater.inflate(R.layout.row_main,parent,false)
        val nameTextView = row.findViewById<TextView>(R.id.name_textView)
        nameTextView.text = list[position].id.toString() + " " + list[position].nombre +" " + list[position].apellido
        return row
    }
}*/