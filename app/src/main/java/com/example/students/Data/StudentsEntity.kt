package com.example.students.Data

import java.util.*

data class StudentsEntity (
    var id:Int,
    var nombre:String,
    var apellido:String,
    var correo:String,
    var telefono:String,
    var gender:Int,
    var birthday: String


){ constructor():this(0,"","","","",0,"")}