package com.example.students

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_students)

        listStudents = studentsDb.studentsGetAll()
        ltvDeleteStudent.adapter = customerAdapter(this,listStudents!!)

        ltvDeleteStudent.setOnItemClickListener { parent, view, position, id ->
           val student = listStudents!![position]
           // customAlertDialog("¿Seguro que desea eliminar el estudiante seleccionado?",student).show()
            miDialogo("Seguro que desea eliminar el estudiante seleccionado?", student).show()
        }
    }

    private fun miDialogo(message:String, student: StudentsEntity) : AlertDialog{
        val miAlerta = AlertDialog.Builder(this@DeleteStudents)
        miAlerta.setMessage(message)
        miAlerta.setPositiveButton("SI") { dialog, which ->
            studentsDb.studentDelete(student.id)
            Toast.makeText(this@DeleteStudents, "Estudiante eliminado", Toast.LENGTH_SHORT).show()
            ltvDeleteStudent.adapter = customerAdapter(this,listStudents!!)
        }
        miAlerta.setNegativeButton("NO") { dialog, which ->
            Toast.makeText(this@DeleteStudents, "Estudiante no eliminado", Toast.LENGTH_SHORT).show()
        }
        return miAlerta.create()
    }

    /*private fun customAlertDialog(message: String,student: StudentsEntity) : AlertDialog {
        val alert = AlertDialog.Builder(this).apply {
            setMessage(message)
        }
        alert.setPositiveButton("SI") { dialog, which ->
            if (studentsDb.studentDelete(student.id) != -1 ) {
                Toast.makeText(this@DeleteStudents,"Estudiante Eliminado",Toast.LENGTH_LONG).show()
                customAdapter(this,listStudents!!).notifyDataSetChanged()
                ltvDeleteStudent.adapter = customerAdapter(this, listStudents!!)
            }else{
                Toast.makeText(this@DeleteStudents,"Estudiante no se pudo eliminar",Toast.LENGTH_LONG).show()
            }
        }
        alert.setNegativeButton("NO") { dialog, which ->
        }
        return alert.create()
    }*/

}
class customAdapter (context: Context, private val list: ArrayList<StudentsEntity>):
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
}