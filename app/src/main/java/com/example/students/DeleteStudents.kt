package com.example.students

import android.annotation.SuppressLint
import android.content.Context
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
import androidx.appcompat.app.AlertDialog
import com.example.students.Data.StudentsDb
import com.example.students.Data.StudentsEntity
import kotlinx.android.synthetic.main.activity_delete_students.*

class DeleteStudents : AppCompatActivity() {

    private var listStudents:ArrayList<StudentsEntity>? = null
    private val studentsDb = StudentsDb(this)
    private var student = StudentsEntity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_students)

        //listStudents = studentsDb.studentsGetAll()
        //ltvDeleteStudent.adapter = customerAdapter(this,listStudents!!)

        val myAdapter = ArrayAdapter<String>(this@DeleteStudents,android.R.layout.simple_list_item_1,studentsDb.studentsGetAll())
        ltvDeleteStudent.adapter = myAdapter

        ltvDeleteStudent.setOnItemClickListener { adapterView: AdapterView<*>, view, position, id ->

            //val student = listStudents!![position]
            val item = adapterView.getItemAtPosition(position).toString()
            val splitItem = item.split(" ")
            student.id = splitItem[0].toInt()
            customAlertDialog("¿Seguro que desea eliminar el estudiante seleccionado?",student).show()
        }
    }

    private fun customAlertDialog(message: String,student:StudentsEntity) : AlertDialog {

        val alert = AlertDialog.Builder(this).apply {
            setMessage(message)
        }
        alert.setPositiveButton("SI") { dialog, which ->
            if (studentsDb.studentDelete(student.id) != -1 ) {
                Log.d("BOSS","I´m the boss")
                Toast.makeText(this@DeleteStudents,"Estudiante Eliminado",Toast.LENGTH_LONG).show()
                finish();
                startActivity(intent);
                //customAdapter(this,listStudents!!).notifyDataSetChanged()
            }else{
                Log.d("BOSS","I´m not the boss")
                Toast.makeText(this@DeleteStudents,"Estudiante no se pudo eliminar",Toast.LENGTH_LONG).show()
            }
        }
        alert.setNegativeButton("NO") { dialog, which ->
        }
        return alert.create()
    }
}
/*class CustomAdapter (context: Context, private val list: ArrayList<StudentsEntity>):
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
