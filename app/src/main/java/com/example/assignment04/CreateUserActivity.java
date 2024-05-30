package com.example.assignment04;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CreateUserActivity extends AppCompatActivity {

    TextView countryName, dobText;
    final String[] countries = Data.getCountryName();
    String selectedCountry;

    public static final String USER_KEY = "USER";

    String name, email, age, country, dateOfBirth;
    EditText nameText, emailText, ageText;


String pickedDate;

    private ActivityResultLauncher<Intent> startDateForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == RESULT_OK){
                Intent data = result.getData();

                pickedDate = data.getStringExtra(DoBActivity.KEY_DATE);
                dobText.setText(pickedDate);

            }
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        countryName = findViewById(R.id.countryText);
        dobText = findViewById(R.id.dobText);
        nameText = findViewById(R.id.nameText);
        emailText = findViewById(R.id.emailText);
        ageText = findViewById(R.id.ageText);





        findViewById(R.id.countrySelect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CreateUserActivity.this);
                int checkedItem = -1;
                builder.setTitle("Choose a Country.")
                        .setSingleChoiceItems(countries, checkedItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedCountry = countries[which];
                    }
                }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                    countryName.setText(selectedCountry);


                            }

                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                builder.create().show();

            }
        });

        findViewById(R.id.dobSelect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent  = new Intent(CreateUserActivity.this, DoBActivity.class);
            startDateForResult.launch(intent);
            }
        });

        findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                name = nameText.getText().toString();
                email = emailText.getText().toString();
                age = ageText.getText().toString();
                country = countryName.getText().toString();
                dateOfBirth = dobText.getText().toString();

                if (name.isEmpty()) {
                    Toast.makeText(CreateUserActivity.this, "Enter a Name Please!", Toast.LENGTH_SHORT).show();
                } else if (email.isEmpty()) {
                    Toast.makeText(CreateUserActivity.this, "Enter an Email Please!", Toast.LENGTH_SHORT).show();
                }else if (age.isEmpty()) {
                    Toast.makeText(CreateUserActivity.this, "Enter an Age Please!", Toast.LENGTH_SHORT).show();
                }else if (country.equals("N/A")) {
                    Toast.makeText(CreateUserActivity.this, "Enter a Country Please!", Toast.LENGTH_SHORT).show();
                }else if (dateOfBirth.equals("N/A")) {
                    Toast.makeText(CreateUserActivity.this, "Enter a Date of Birth Please!", Toast.LENGTH_SHORT).show();
                }else{
                    User profile = new User(name, email, age, country, dateOfBirth);
                    Intent intent = new Intent(CreateUserActivity.this, ProfileActivity.class);
                    intent.putExtra(USER_KEY, profile);
                    startActivity(intent);
                    finish();
                }
/*



                    */


            }
        });
    }
}