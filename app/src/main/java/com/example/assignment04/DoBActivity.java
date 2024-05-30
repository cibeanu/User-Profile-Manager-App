package com.example.assignment04;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DoBActivity extends AppCompatActivity {

    CalendarView calendarView;
    public static long date;
    public static final String KEY_DATE = "DATE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_do_bactivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        calendarView = findViewById(R.id.calendarView);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, +18);

        calendarView.setMaxDate(calendar.getTimeInMillis());


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String selectedDate = (month+1) + "/"+dayOfMonth+"/"+year;
                calendar.set(year, month, dayOfMonth);


                date = calendar.getTimeInMillis();
                Log.d("date", selectedDate);

            }
        });
        findViewById(R.id.calendarCancelButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        findViewById(R.id.calendarSubmitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Date dated = new Date(date);
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                String formatted = format.format(dated);
                Log.d("userDate", "date is " + formatted);

                Intent intent = new Intent();
                intent.putExtra(KEY_DATE, formatted);
                setResult(RESULT_OK, intent);
                finish();

            }
        });
    }
}