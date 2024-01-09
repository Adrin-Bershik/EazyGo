package com.example.eazygo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;

import java.security.Signature;

public class MainActivity<signupActivity> extends AppCompatActivity {

    EditText signupName,signupEmail,signupUsername,signupPassword;
    TextView loginRedirectText;
    Button signupButton;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signupName=findViewById(R.id.username);
        signupEmail=findViewById(R.id.Email);
        signupUsername=findViewById(R.id.Username);
        signupPassword=findViewById(R.id.Password);
        signupButton=findViewById(R.id.loginbtn);
        loginRedirectText=findViewById(R.id.forgotpass);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = signupName.getText().toString().trim();
                String email = signupEmail.getText().toString().trim();
                String username = signupUsername.getText().toString().trim();
                String password = signupPassword.getText().toString().trim();

                // Check if any of the fields is empty yes
                if (name.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // All fields are filled, proceed with sign-up
                    database = FirebaseDatabase.getInstance();
                    reference = database.getReference("users");

                    HelperClass helperClass = new HelperClass(name, email, username, password);
                    reference.child(name).setValue(helperClass);

                    Toast.makeText(MainActivity.this, "You have signed up successfully!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(intent);
                }
            }
        });


        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });


        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this,R.color.colorAc));
    }
}