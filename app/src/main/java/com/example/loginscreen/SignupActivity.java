package com.example.loginscreen;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.loginscreen.databinding.ActivitySignupBinding;

public class SignupActivity extends AppCompatActivity {

    ActivitySignupBinding binding;
    Database dbSQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbSQLite = new Database(this);

        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String signupUsername = binding.signupUsername.getText().toString();
                String signupPassword = binding.signupPassword.getText().toString();
                String confirmPassword = binding.confirmPassword.getText().toString();

                if (signupUsername.isEmpty() || signupPassword.isEmpty() || confirmPassword.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check if passwords match
                if (!signupPassword.equals(confirmPassword)) {
                    Toast.makeText(SignupActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check if username already exists
                if (dbSQLite.checkUsername(signupUsername)) {
                    Toast.makeText(SignupActivity.this, "User exists, please try another Username!", Toast.LENGTH_SHORT).show();
                } else {
                    // If not, insert the new user
                    if (dbSQLite.insertData(signupUsername, signupPassword)) {
                        Toast.makeText(SignupActivity.this, "Account Registered!", Toast.LENGTH_SHORT).show();

                        // Redirect to Login Activity
                        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();  // Close the SignupActivity
                    } else {
                        Toast.makeText(SignupActivity.this, "Error registering user!", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });

        binding.loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
