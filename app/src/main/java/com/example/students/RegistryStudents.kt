package com.example.students

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.students.Data.StudentsDb
import com.example.students.Data.StudentsEntity
import kotlinx.android.synthetic.main.activity_registry_students.*

class RegistryStudents : AppCompatActivity() {

    val students = StudentsEntity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registry_students)

        btnEnviar.setOnClickListener {
            val studentsDb = StudentsDb(this)
            //val index = studentsDb.studentAdd(students)
            //Log.d("A DORMIR","$index")

            students.nombre = edtNombre.text.toString()
            students.apellido = edtApellido.text.toString()
            students.correo = edtCorreo.text.toString()
            students.telefono = edtTelefono.text.toString()
            students.gender = spnGenero.getSelectedItemPosition()
            //students.birthday = datePicker.


            val index = studentsDb.studentAdd(students)

            Log.d("BossIndex","$index")

        }

    }
}
