package com.example.students

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.registryStudents->{
                val intent = Intent(this@MainActivity,RegistryStudents::class.java)
                startActivity(intent)
            }
            R.id.showStudents->{
                val intent = Intent(this@MainActivity,ShowStudents::class.java)
                startActivity(intent)
            }
            R.id.editStudents->{
                val intent = Intent(this@MainActivity,EditStudents::class.java)
                startActivity(intent)
            }
            R.id.detailStudents->{
                val intent = Intent(this@MainActivity,DetailStudents::class.java)
                startActivity(intent)
            }
            R.id.deleteStudents->{
                val intent = Intent(this@MainActivity,DeleteStudents::class.java)
                startActivity(intent)
            }
            else -> super.onOptionsItemSelected(item)
        }

        return true
    }
}
