package com.example.air_guardiansproject;

import android.content.Intent;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class LogInActivity extends AppCompatActivity {
    TextView registerText;
    Button backButton;
    Button toHomeButton;
    EditText email;
    EditText password;
    EditText confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        registerText = (TextView)findViewById(R.id.text_register);
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this, Register.class);
                startActivity(intent);
            }
        });

        backButton = (Button)findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        toHomeButton = (Button)findViewById(R.id.button);
        toHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = (EditText)findViewById(R.id.email_login);
                password = (EditText)findViewById(R.id.password_login);

                String dataEmail = email.getText().toString();
                String dataPassword = password.getText().toString();

                if(dataEmail.equals("") || dataPassword.equals("")){
                    Toast.makeText(getApplicationContext(), "No empty box allowed!", Toast.LENGTH_SHORT).show();
                }
                else{
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        String url = "jdbc:mysql://mds-project.mysql.database.azure.com:3306/mydb";
                        String user = "mdsproject@mds-project";
                        String pass = "qwer1234!@#$";
                        Connection dbConn = getConnection(url, user, pass);

                        boolean accountExists = false;
                        try {
                            Statement stmt = (Statement) dbConn.createStatement();
                            ResultSet res = stmt.executeQuery("Select * from clients");
                            while (res.next()) {
                                if(res.getString("email_client").equals(dataEmail) && res.getString("password_client").equals(dataPassword)){
                                    accountExists = true;
                                    break;
                                }
                            }
                            System.out.println(accountExists);
                            if(accountExists){
                                User.user_id=res.getInt("id_client");
                                User.getFavorites();
                                Toast.makeText(getApplicationContext(), "Successfully logged in!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LogInActivity.this, Home.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(getApplicationContext(), "The email/password is incorrect!", Toast.LENGTH_SHORT).show();
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
            }
        });
    }
}
