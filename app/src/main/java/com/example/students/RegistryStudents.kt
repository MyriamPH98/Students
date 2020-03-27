package com.example.students

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.students.Data.StudentsDb
import com.example.students.Data.StudentsEntity
import kotlinx.android.synthetic.main.activity_registry_students.*
import java.util.*

class RegistryStudents : AppCompatActivity(){

    val students = StudentsEntity()
    val calendar = Calendar.getInstance()

    var date: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registry_students)

        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ) { view, year, month, day ->

            var newMonth = month + 1
            var stringMonth = ""
            var stringDay = ""

            if (newMonth >= 10){
                stringMonth = "$newMonth"
            }else{
                stringMonth = "0$newMonth"
            }
            if (day >= 10 ){
                stringDay = "$day"
            }else{
                stringDay = "0$day"
            }
            date = "$year-$stringMonth-$stringDay"
        }

        btnEnviar.setOnClickListener {
            val studentsDb = StudentsDb(this)

            if(edtNombre.text.toString().trim().isNotEmpty()){
                students.nombre = edtNombre.text.toString()
                if(edtApellido.text.toString().trim().isNotEmpty()){
                    students.apellido = edtApellido.text.toString()
                    if(edtCorreo.text.toString().trim().isNotEmpty()){
                        students.correo = edtCorreo.text.toString()
                        if(edtTelefono.text.toString().isNotEmpty()){
                            students.telefono = edtTelefono.text.toString()
                                students.birthday = date
                                if (spnGenero!!.selectedItem.equals("Genero")){
                                    Toast.makeText(this@RegistryStudents, "Seleccione un genero", Toast.LENGTH_LONG).show()
                                } else{
                                    students.gender = spnGenero.getSelectedItemPosition()
                                    studentsDb.studentAdd(students)
                                    cleanControls()
                                    Toast.makeText(this@RegistryStudents,"Estudiante Agregado",Toast.LENGTH_LONG).show()
                                }

                        }else{
                            Toast.makeText(this@RegistryStudents, "Ingrese un número de teléfono",Toast.LENGTH_LONG).show()
                        }
                    }else{
                        Toast.makeText(this@RegistryStudents, "Ingrese un correo electrónico",Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(this@RegistryStudents, "Ingrese un apellido",Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(this@RegistryStudents, "Ingrese un nombre", Toast.LENGTH_LONG).show()
            }
            /*students.nombre = edtNombre.text.toString()
            students.apellido = edtApellido.text.toString()
            students.correo = edtCorreo.text.toString()
            students.telefono = edtTelefono.text.toString()
            students.gender = spnGenero.getSelectedItemPosition()
            students.birthday = date*/
        }
    }

    private fun cleanControls() {
        edtNombre.text.clear()
        edtApellido.text.clear()
        edtCorreo.text.clear()
        edtTelefono.text.clear()
        spnGenero.setSelection(0)
        datePicker.updateDate(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH))
    }
}

