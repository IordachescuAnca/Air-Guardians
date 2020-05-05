package com.example.airguardians;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    EditText email, password, confirmedPassword;
    Button registerButton;
    DataBaseService db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DataBaseService(this);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        confirmedPassword = (EditText)findViewById(R.id.cpassword);
        registerButton = (Button)findViewById(R.id.button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dataEmail = email.getText().toString();
                String dataPassword = password.getText().toString();
                String dataConfirmedPassword = confirmedPassword.getText().toString();
                if (dataEmail.compareTo("") == 0 || dataPassword.compareTo("") == 0 || dataConfirmedPassword.compareTo("") == 0){
                    Toast.makeText(getApplicationContext(), "Fields are empty!", Toast.LENGTH_SHORT).show();
                }
                else{
                    if (dataPassword.compareTo(dataConfirmedPassword) == 0){
                        boolean canInsert = db.checkEmail(dataEmail);
                        if (canInsert){
                            boolean ID = db.insertData(dataEmail, dataPassword);
                            if(ID){
                                Toast.makeText(getApplicationContext(), "Registered succesfully!", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Email already exists!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Password and confirm password do not match!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
