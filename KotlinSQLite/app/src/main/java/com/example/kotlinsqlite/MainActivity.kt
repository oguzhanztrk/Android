package com.example.kotlinsqlite

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        try {
            val myDatabase = this.openOrCreateDatabase("Musicians", Context.MODE_PRIVATE,null)
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS musicians (id INTEGER PRIMARY KEY,name VARCHAR,age INT)")
            //myDatabase.execSQL("INSERT INTO musicians (name,age) VALUES ('James',50)")
            //myDatabase.execSQL("INSERT INTO musicians (name,age) VALUES ('Lars',60)")
            //myDatabase.execSQL("INSERT INTO musicians (name,age) VALUES ('Kirk',55)")

            // myDatabase.execSQL("UPDATE musicians SET age = 61 Where name = 'Lars'")
            myDatabase.execSQL("DELETE FROM musicians WHERE name = 'Lars'")
            val cursor = myDatabase.rawQuery("SELECT * FROM musicians ",null)
            //val cursor = myDatabase.rawQuery("SELECT * FROM musicians WHERE name = 'James'",null)
            //val cursor = myDatabase.rawQuery("SELECT * FROM musicians WHERE name LIKE '%s'",null)

            val nameIx = cursor.getColumnIndex("name")
            val ageIx = cursor.getColumnIndex("age")
            val idIx = cursor.getColumnIndex("id")
            while (cursor.moveToNext()){
                println("Name : " + cursor.getString(nameIx))
                println("Age : " + cursor.getInt(ageIx))
                println("Id : " + cursor.getInt(idIx))
            }
            cursor.close()

        } catch (e:Exception){
            println(e.message)
        }
    }
}
