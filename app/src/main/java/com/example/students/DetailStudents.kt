package com.example.students

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.students.Data.StudentsDb
import kotlinx.android.synthetic.main.activity_detail_students.*

class DetailStudents : AppCompatActivity() {

    private val studentsDb = StudentsDb(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_students)

        var idStudent = intent.getIntExtra("ID",-1)

        if (idStudent != -1) {
            var student = studentsDb.studentGetOne(idStudent = idStudent)

            txvNombre.text = student.nombre
            txvApellido.text = student.apellido
            txvCorreo.text = student.correo
            txvTelefono.text = student.telefono
            txvFecha.text = student.birthday

            if (student.gender == 1) {
                txvGenero.text = "Femenino"
            }else{
                txvGenero.text = "Masculino"
            }
        }
    }
}
