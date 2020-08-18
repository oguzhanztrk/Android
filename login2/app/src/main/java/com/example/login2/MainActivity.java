package com.example.login2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
    static String userName = "noins";
    static String password = "123oguz321";
    static String dbUrl = "jdbc:postgresql://localhost:5432/postgres";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            Connection connection = null;
            Statement statement = null;
            ResultSet resultSet = null;

            try {
                connection =  DriverManager.getConnection(dbUrl,userName,password);
                System.out.println("Bağlantı kuruldu.");
                statement = connection.createStatement();
                resultSet = statement.executeQuery("Select * from User");
                while( resultSet.next())
                {
                    System.out.println(resultSet.getString("NAME"));
                }

            } catch (SQLException exception) {
                System.out.println(exception.getMessage());
            }
            /*finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }*/
        }

    }


