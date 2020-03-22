package com.example.students

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail_students.*

class DetailStudents : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_students)

        txvNombre.text = intent.getStringExtra("Name")
        txvApellido.text = intent.getStringExtra("LastName")
        txvCorreo.text = intent.getStringExtra("Email")
        txvTelefono.text = intent.getStringExtra("Phone")
        txvFecha.text = intent.getStringExtra("Date")
        val gender = intent.getIntExtra("Gender",-1)

        if (gender != -1){
            if (gender == 1) {
                txvGenero.text = "Femenino"
            }else{
                txvGenero.text = "Masculino"
            }
        }
    }
}
