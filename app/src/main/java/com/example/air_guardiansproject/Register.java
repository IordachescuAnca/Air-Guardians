package com.example.air_guardiansproject;

import android.content.Intent;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class Register extends AppCompatActivity {
    EditText email;
    EditText password;
    EditText confirmPassword;
    Button registerButton;
    Button backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        email = (EditText)findViewById(R.id.email_register);
        password = (EditText)findViewById(R.id.password_register);
        confirmPassword = (EditText)findViewById(R.id.confirm_password_register);
        registerButton = (Button)findViewById(R.id.button_register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dataEmail = email.getText().toString();
                String dataPassword = password.getText().toString();
                String dataConfirmedPassword = confirmPassword.getText().toString();
                if(dataEmail.equals("") || dataPassword.equals("") || dataConfirmedPassword.equals("")){
                    Toast.makeText(getApplicationContext(), "No empty box allowed!", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(dataPassword.equals(dataConfirmedPassword)){
                        try {
                            Class.forName("com.mysql.jdbc.Driver");
                            String url = "jdbc:mysql://mds-project.mysql.database.azure.com:3306/mydb";
                            String user = "mdsproject@mds-project";
                            String pass = "qwer1234!@#$";
                            Connection dbConn = getConnection(url, user, pass);
                            try {
                                Statement stmt = (Statement) dbConn.createStatement();
                                ResultSet res = stmt.executeQuery("Select * from clients");
                                boolean emailExists = false;
                                while (res.next()) {
                                    if(res.getString("email_client").equals(dataEmail)){
                                        emailExists = true;
                                        Toast.makeText(getApplicationContext(), "Email already exists!", Toast.LENGTH_SHORT).show();
                                        break;
                                    }
                                }
                                if(!emailExists){
                                    PreparedStatement statement = (PreparedStatement) dbConn.prepareStatement("INSERT INTO clients (email_client, password_client) VALUES ( ?, ?)");
                                    statement.setString(1, dataEmail);
                                    statement.setString(2, dataPassword);
                                    statement.execute();
                                    Toast.makeText(getApplicationContext(), "Registered succesfully!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Register.this, LogInActivity.class);
                                    startActivity(intent);
                                }
                            }
                            catch(SQLException e){
                                e.printStackTrace();
                            }

                            dbConn.close();

                        } catch (ClassNotFoundException | SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Password and confirmed password do not match!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        backButton = (Button)findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
