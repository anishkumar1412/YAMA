package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class monthly_page extends AppCompatActivity {

    private EditText monthly_pickup_date_text, delivertime;
    //    private Button btnAddMonth;
    private ImageButton monthly_pickup_date_button;
    private ImageButton btnAddTimeDeliver;
    private Button bookButtonMonthly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_page);

        monthly_pickup_date_text = findViewById(R.id.monthly_pickup_date_text);
//        btnAddMonth = findViewById(R.id.btnAddMonth);
        monthly_pickup_date_button = findViewById(R.id.monthly_pickup_date_button);
        bookButtonMonthly = findViewById(R.id.bookButtonMonthly);
//        delivertime = findViewById(R.id.delivertime);
//        btnAddTimeDeliver = findViewById(R.id.btnAddTimeDeliver);

//        btnAddMonth.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addMonth();
//            }
//        });
        bookButtonMonthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create intent to start the hourly_page activity
                Intent intent = new Intent(monthly_page.this, booking_confirmation_page_monthly.class); // Assuming HourlyPage is the correct activity name
                startActivity(intent);
                // Dismiss the dialog after starting the activity
            }
        });

        monthly_pickup_date_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDatePicker();
            }
        });

//        btnAddTimeDeliver.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openDatePicker();
//            }
//        });


        Spinner spinner = findViewById(R.id.spinner_main);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(monthly_page.this, "Selected Item: " + item, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Code to handle no item selected event
            }
        });

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Self Pickup");
        arrayList.add("Delivery");


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayList);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner.setAdapter(adapter);

    }

//    private void openTimePicker() {
//        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
//            @Override
//            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
//                // Showing the picked value in the textView
//                editText.setText(String.valueOf(hour) + ":" + String.valueOf(minute));
//            }
//        }, 15, 30, false);
//
//        timePickerDialog.show();
//    }

    //    private void openTimePickerDeliver() {
//        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
//            @Override
//            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
//                // Showing the picked value in the textView
//                delivertime.setText((hour) + ":" + String.valueOf(minute));
//            }
//        }, 15, 30, false);
//
//        timePickerDialog.show();
//    }
//
    private void openDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                //Showing the picked value in the textView
                monthly_pickup_date_text.setText(String.valueOf(year) + "." + String.valueOf(month) + "." + String.valueOf(day));

            }
        }, 2023, 1, 20);

        datePickerDialog.show();

    }
}

