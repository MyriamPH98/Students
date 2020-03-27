package com.example.students.Data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import java.lang.Exception

class StudentsDb {

    private var connectionDb : ConnectionDb
    private lateinit var sqliteDataBase: SQLiteDatabase

    constructor(context: Context){
        connectionDb = ConnectionDb(context)
    }

    fun studentAdd(student: StudentsEntity): Int {
        var lastId = -1

        sqliteDataBase = connectionDb.openConnection(ConnectionDb.MODE_WRITE)

        val values = ContentValues()
        values.put(NOMBRE,student.nombre)
        values.put(APELLIDO,student.apellido)
        values.put(CORREO,student.correo)
        values.put(TELEFONO,student.telefono)
        values.put(GENERO,student.gender)
        values.put(BIRTHDAY,student.birthday)

        sqliteDataBase.insert(ConnectionDb.TABLE_NAME_STUDENTS,null,values)
        sqliteDataBase.close()
        lastId = lastInsert()
        return lastId
    }

    fun  studentEdit(student:StudentsEntity): Int {
        var result = -1
        sqliteDataBase = connectionDb.openConnection(ConnectionDb.MODE_WRITE)

        val values = ContentValues()
        values.put(NOMBRE,student.nombre)
        values.put(APELLIDO,student.apellido)
        values.put(CORREO,student.correo)
        values.put(TELEFONO,student.telefono)
        values.put(GENERO,student.gender)
        values.put(BIRTHDAY,student.birthday)
        var selection = "Id=?"
        var args = arrayOf(student.id.toString())

        try {
            sqliteDataBase.update(ConnectionDb.TABLE_NAME_STUDENTS,values,selection,args)
            sqliteDataBase.close()

            result = 1
        }catch (e: Exception){
        }

        return result
    }

    fun  studentDelete(idStudent:Int): Int {
        var result = -1
        sqliteDataBase = connectionDb.openConnection(ConnectionDb.MODE_WRITE)

        try {
            sqliteDataBase.delete(ConnectionDb.TABLE_NAME_STUDENTS, "$ID=$idStudent",null)
            sqliteDataBase.close()

            result = 1

        }catch (e: Exception){
        }

        return result
    }

    fun studentsGetAll(): Array<String> {

        var list = ArrayList<String>()
        var student = StudentsEntity()

        sqliteDataBase = connectionDb.openConnection(ConnectionDb.MODE_READ)
        val fields = arrayOf(ID, NOMBRE, APELLIDO, CORREO, TELEFONO, GENERO,BIRTHDAY)
        val cursor = sqliteDataBase.query(ConnectionDb.TABLE_NAME_STUDENTS,fields,null,null,null,null,null)

        if(cursor.moveToFirst()){
            do {
                student.id = cursor.getInt(0)
                student.nombre = cursor.getString(1)
                student.apellido = cursor.getString(2)
                student.correo = cursor.getString(3)
                student.telefono = cursor.getString(4)
                student.gender = cursor.getInt(5)
                student.birthday = cursor.getString(6).toString()

                list.add("${student.id} ${student.nombre} ${student.apellido}")
                student = StudentsEntity()
            }while (cursor.moveToNext())
        }

        sqliteDataBase.close()

        return  list.toTypedArray()
    }

    fun studentGetOne(idStudent:Int): StudentsEntity {

        var student = StudentsEntity()

        sqliteDataBase = connectionDb.openConnection(ConnectionDb.MODE_READ)
        val fields = arrayOf(ID, NOMBRE, APELLIDO, CORREO,TELEFONO, GENERO, BIRTHDAY)
        var selection = "Id=?"
        var args = arrayOf(idStudent.toString())
        val cursor = sqliteDataBase.query(ConnectionDb.TABLE_NAME_STUDENTS,fields,selection,args,null,null,null)

        if(cursor.moveToFirst()) {
            student.id = cursor.getInt(0)
            student.nombre = cursor.getString(1)
            student.apellido = cursor.getString(2)
            student.correo = cursor.getString(3)
            student.telefono = cursor.getString(4)
            student.gender = cursor.getInt(5)
            student.birthday = cursor.getString(6)
        }
        sqliteDataBase.close()
        return student
    }

    private fun lastInsert(): Int {
        var lastId = -1
        sqliteDataBase = connectionDb.openConnection(ConnectionDb.MODE_READ)
        val cursor = sqliteDataBase.rawQuery("SELECT IFNULL(MAX("+ ID+"),-1) FROM CTL_ESTUDIANTES",null)
        if (cursor.moveToFirst()){
            lastId = cursor.getInt(0)
        }
        return lastId
    }


    companion object{
        const val ID = "Id"
        const val NOMBRE = "Nombre"
        const val APELLIDO = "Apellido"
        const val CORREO = "Correo"
        const val TELEFONO = "Telefono"
        const val GENERO = "Genero"
        const val BIRTHDAY = "Birthday"
    }
}