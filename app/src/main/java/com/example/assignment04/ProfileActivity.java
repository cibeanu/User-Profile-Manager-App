package com.example.assignment04;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfileActivity extends AppCompatActivity {

    TextView profileName, profileEmail, profileAge, profileCountry, profileDate;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        profileName = findViewById(R.id.profileName);
        profileEmail = findViewById(R.id.profileEmail);
        profileAge = findViewById(R.id.profileAge);
        profileCountry = findViewById(R.id.profileCountry);
        profileDate = findViewById(R.id.profileDate);
        if(getIntent()!= null && getIntent().hasExtra(CreateUserActivity.USER_KEY)){
            user = (User) getIntent().getSerializableExtra(CreateUserActivity.USER_KEY);
            profileName.setText(user.getName());
            profileEmail.setText(user.getEmail());
            profileAge.setText(user.getAge());
            profileCountry.setText(user.getCountry());
            profileDate.setText(user.getDateOfBirth());
        }
    }
}