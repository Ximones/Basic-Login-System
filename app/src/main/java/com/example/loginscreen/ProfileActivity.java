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
        String encryptedPassword = getIntent().getStringExtra("encrypted_password");

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
                    passwordText.setText("Password: " + decryptPassword(encryptedPassword));
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

    private String encryptPassword(String password){
        try {
            String encryptedMsg = AESCrypt.encrypt(password, message);
        }catch (GeneralSecurityException e){
            //handle error
        }
    }
    // A placeholder for your decryption method
    private String decryptPassword(String encryptedPassword) {
        // TODO: Implement your decryption logic here
        // For now, we'll just return the encrypted password for demonstration
        return encryptedPassword; // Replace this with actual decryption
    }
}
