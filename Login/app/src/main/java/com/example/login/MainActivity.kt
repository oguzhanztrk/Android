package com.example.login

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.Exception
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T






class MainActivity : AppCompatActivity() {
    var userName = "noins"
    var password = "123oguz321"
    var dbUrl = "jdbc:postgresql://localhost:5432/postgres"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var conn: Connection? = null


        try {
            conn = DriverManager.getConnection(dbUrl,userName,password);
            System.out.println("Bağlantı kuruldu.");
        } catch (ex:Exception) {
            System.out.println(ex.message);
        }
        finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}





}

}
