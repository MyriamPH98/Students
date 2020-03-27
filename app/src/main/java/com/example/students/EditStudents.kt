package com.example.students

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.students.Data.StudentsDb
import com.example.students.Data.StudentsEntity
import kotlinx.android.synthetic.main.activity_edit_students.*
import java.util.*

class EditStudents : AppCompatActivity() {

    val studentsDb = StudentsDb(this)
    val students = StudentsEntity()
    val calendar = Calendar.getInstance()
    var date: String? = null
    var idStudent = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_students)

        idStudent = intent.getIntExtra("ID",-1)

        if (idStudent != -1) {
            var student = studentsDb.studentGetOne(idStudent = idStudent)
            edtEditNombre.setText(student.nombre)
            edtEditApellido.setText(student.apellido)
            edtEditCorreo.setText(student.correo)
            edtEditTelefono.setText(student.telefono)
            date = student.birthday
            val splitDate = date!!.split("-")
            val oldMonth = splitDate[1].toInt() - 1

            dateEditPicker.init(splitDate[0].toInt(),oldMonth,splitDate[2].toInt()) { view, year, month, day ->

                var newMonth = month + 1
                var stringMonth = ""
                var stringDay = ""

                if (newMonth >= 10) {
                    stringMonth = "$newMonth"
                }else{
                    stringMonth = "0$newMonth"
                }

                if (day >= 10 ) {
                    stringDay = "$day"
                }else{
                    stringDay = "0$day"
                }

                date = "$year-$stringMonth-$stringDay"

            }

            spnEditGenero.setSelection(student.gender)

            btnEditEnviar.setOnClickListener {

                val studentsDb = StudentsDb(this)
                students.id = idStudent

                if(edtEditNombre.text.toString().trim().isNotEmpty()){
                    students.nombre = edtEditNombre.text.toString()
                    if(edtEditApellido.text.toString().trim().isNotEmpty()){
                        students.apellido = edtEditApellido.text.toString()
                        if(edtEditCorreo.text.toString().trim().isNotEmpty()){
                            students.correo = edtEditCorreo.text.toString()
                            if(edtEditTelefono.text.toString().isNotEmpty()){
                                students.telefono = edtEditTelefono.text.toString()
                                students.birthday = date!!
                                if (spnEditGenero!!.selectedItem.equals("Genero")){
                                    Toast.makeText(this@EditStudents, "Seleccione un genero", Toast.LENGTH_LONG).show()
                                } else{
                                    val index = studentsDb.studentEdit(students)
                                    if (index == 1){
                                        Log.d("UDELP","$index")
                                        cleanControls()
                                        Toast.makeText(this@EditStudents,"Estudiante Editado", Toast.LENGTH_LONG).show()
                                    }else{
                                        Toast.makeText(this@EditStudents,"Estudiante no se pudo editar", Toast.LENGTH_LONG).show()
                                    }
                                }
                            }else{
                                Toast.makeText(this@EditStudents, "Ingrese un número de teléfono",Toast.LENGTH_LONG).show()
                            }
                        }else{
                            Toast.makeText(this@EditStudents, "Ingrese un correo electrónico",Toast.LENGTH_LONG).show()
                        }
                    }else{
                        Toast.makeText(this@EditStudents, "Ingrese un apellido",Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(this@EditStudents, "Ingrese un nombre", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun cleanControls() {
        edtEditNombre.text.clear()
        edtEditApellido.text.clear()
        edtEditCorreo.text.clear()
        edtEditTelefono.text.clear()
        spnEditGenero.setSelection(0)
        dateEditPicker.updateDate(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH))
    }
}
