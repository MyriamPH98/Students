package com.example.students

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.students.Data.StudentsDb
import com.example.students.Data.StudentsEntity
import kotlinx.android.synthetic.main.activity_detail_students.*
import kotlinx.android.synthetic.main.activity_edit_students.*
import java.util.*

class EditStudents : AppCompatActivity() {

    val students = StudentsEntity()
    val calendar = Calendar.getInstance()
    var date: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_students)

        edtEditNombre.setText(intent.getStringExtra("EditName"))
        edtEditApellido.setText(intent.getStringExtra("EditLastName"))
        edtEditCorreo.setText(intent.getStringExtra("EditEmail"))
        edtEditTelefono.setText(intent.getStringExtra("EditPhone"))
        date = intent.getStringExtra("EditDate")
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

        val gender = intent.getIntExtra("EditGender",-1)

        if (gender != -1) {
            spnEditGenero.setSelection(gender)
        }

        btnEditEnviar.setOnClickListener {

            val studentsDb = StudentsDb(this)

            students.id = intent.getIntExtra("ID",-1)
            students.nombre = edtEditNombre.text.toString()
            students.apellido = edtEditApellido.text.toString()
            students.correo = edtEditCorreo.text.toString()
            students.telefono = edtEditTelefono.text.toString()
            students.gender = spnEditGenero.getSelectedItemPosition()
            students.birthday = date!!

            val index = studentsDb.studentEdit(students)

            if (index == 1){
                cleanControls()
                Toast.makeText(this@EditStudents,"Estudiante Editado", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this@EditStudents,"Estudiante no se pudo editar", Toast.LENGTH_LONG).show()
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
