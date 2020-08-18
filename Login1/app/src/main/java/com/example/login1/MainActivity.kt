package com.example.login1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Connection connection = null;
        dbConnection dbConnection = new dbConnection();
        PreparedStatement statement = null;
        ResultSet resultSet;
    }
}
