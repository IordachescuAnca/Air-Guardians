package com.example.airguardians;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.time.Instant;

public class Register extends AppCompatActivity {

    EditText email;
    EditText password;
    EditText confirm_password;
    Button register_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = (EditText)findViewById(R.id.email_register);
        password = (EditText)findViewById(R.id.password_register);
        confirm_password = (EditText)findViewById(R.id.confirm_password_register);
        register_button = (Button)findViewById(R.id.button_register);
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Registered succesfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Register.this, LogInActivity.class);
                startActivity(intent);
            }
        });
    }
}
