package com.example.loginscreen;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ProfileActivity extends AppCompatActivity {

    private TextView usernameText;
    private TextView passwordText;
    private Button displayPasswordButton;
    private Button logoutButton;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        usernameText = (TextView)findViewById(R.id.usernameText);
        passwordText = (TextView)findViewById(R.id.passwordText);
        displayPasswordButton = findViewById(R.id.displayPasswordButton);
        logoutButton = findViewById(R.id.logoutButton);

        // Get the logged-in user's details from Intent
        String username = getIntent().getStringExtra("username");
        String password = getIntent().getStringExtra("password");
        String encryptedPassword = "****";
        // Display the user's details
        usernameText.setText("Username: " + username);
        passwordText.setText("Encrypted Password: " + encryptedPassword);

        // Set up button to display/hide password
        displayPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible) {
                    // Hide password
                    passwordText.setText("Encrypted Password: " + encryptedPassword);
                    displayPasswordButton.setText("Display Password");
                    isPasswordVisible = false;
                } else {
                    // Show password
                    passwordText.setText("Password: " + password);
                    displayPasswordButton.setText("Hide Password");
                    isPasswordVisible = true;
                }
            }
        });

        // Set up logout button
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle logout
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // Close the ProfileActivity
            }
        });
    }

}
