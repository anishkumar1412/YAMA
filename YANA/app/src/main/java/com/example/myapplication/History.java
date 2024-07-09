package com.example.myapplication;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowInsets;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.Objects;

public class History extends AppCompatActivity {
    ArrayList<RideModel> rideModelArrayList = new ArrayList<>();
    MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        RecyclerView recyclerView = findViewById(R.id.rides_history);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle("History");

        // Adding RideModel data
        rideModelArrayList.add(new RideModel(R.drawable.yanablack, "₹500", "Bara Nimdih Chaibasa", "1 week", "01:26 pm", "25 Mar 2024"));
        rideModelArrayList.add(new RideModel(R.drawable.yanablack, "₹500", "Bara Nimdih Chaibasa", "1 week", "01:26 pm", "25 Mar 2024"));
        rideModelArrayList.add(new RideModel(R.drawable.yanablack, "₹500", "Bara Nimdih Chaibasa", "1 week", "01:26 pm", "25 Mar 2024"));
        rideModelArrayList.add(new RideModel(R.drawable.yanablack, "₹500", "Bara Nimdih Chaibasa", "1 week", "01:26 pm", "25 Mar 2024"));
        rideModelArrayList.add(new RideModel(R.drawable.yanablack, "₹500", "Bara Nimdih Chaibasa", "1 week", "01:26 pm", "25 Mar 2024"));
        rideModelArrayList.add(new RideModel(R.drawable.yanablack, "₹500", "Bara Nimdih Chaibasa", "1 week", "01:26 pm", "25 Mar 2024"));
        rideModelArrayList.add(new RideModel(R.drawable.yanablack, "₹500", "Bara Nimdih Chaibasa", "1 week", "01:26 pm", "25 Mar 2024"));
        rideModelArrayList.add(new RideModel(R.drawable.yanablack, "₹500", "Bara Nimdih Chaibasa", "1 week", "01:26 pm", "25 Mar 2024"));
        rideModelArrayList.add(new RideModel(R.drawable.yanablack, "₹500", "Bara Nimdih Chaibasa", "1 week", "01:26 pm", "25 Mar 2024"));
        rideModelArrayList.add(new RideModel(R.drawable.yanablack, "₹500", "Bara Nimdih Chaibasa", "1 week", "01:26 pm", "25 Mar 2024"));
        rideModelArrayList.add(new RideModel(R.drawable.yanablack, "₹500", "Bara Nimdih Chaibasa", "1 week", "01:26 pm", "25 Mar 2024"));
        rideModelArrayList.add(new RideModel(R.drawable.yanablack, "₹500", "Bara Nimdih Chaibasa", "1 week", "01:26 pm", "25 Mar 2024"));
        rideModelArrayList.add(new RideModel(R.drawable.yanablack, "₹500", "Bara Nimdih Chaibasa", "1 week", "01:26 pm", "25 Mar 2024"));
        rideModelArrayList.add(new RideModel(R.drawable.yanablack, "₹500", "Bara Nimdih Chaibasa", "1 week", "01:26 pm", "25 Mar 2024"));
        rideModelArrayList.add(new RideModel(R.drawable.yanablack, "₹500", "Bara Nimdih Chaibasa", "1 week", "01:26 pm", "25 Mar 2024"));
        rideModelArrayList.add(new RideModel(R.drawable.yanablack, "₹500", "Bara Nimdih Chaibasa", "1 week", "01:26 pm", "25 Mar 2024"));
        rideModelArrayList.add(new RideModel(R.drawable.yanablack, "₹500", "Bara Nimdih Chaibasa", "1 week", "01:26 pm", "25 Mar 2024"));
        rideModelArrayList.add(new RideModel(R.drawable.yanablack, "₹500", "Bara Nimdih Chaibasa", "1 week", "01:26 pm", "25 Mar 2024"));
        rideModelArrayList.add(new RideModel(R.drawable.yanablack, "₹500", "Bara Nimdih Chaibasa", "1 week", "01:26 pm", "25 Mar 2024"));
        rideModelArrayList.add(new RideModel(R.drawable.yanablack, "₹500", "Bara Nimdih Chaibasa", "1 week", "01:26 pm", "25 Mar 2024"));
        rideModelArrayList.add(new RideModel(R.drawable.yanablack, "₹500", "Bara Nimdih Chaibasa", "1 week", "01:26 pm", "25 Mar 2024"));
        rideModelArrayList.add(new RideModel(R.drawable.yanablack, "₹500", "Bara Nimdih Chaibasa", "1 week", "01:26 pm", "25 Mar 2024"));
        rideModelArrayList.add(new RideModel(R.drawable.yanablack, "₹500", "Bara Nimdih Chaibasa", "1 week", "01:26 pm", "25 Mar 2024"));
        rideModelArrayList.add(new RideModel(R.drawable.yanablack, "₹500", "Bara Nimdih Chaibasa", "1 week", "01:26 pm", "25 Mar 2024"));
        rideModelArrayList.add(new RideModel(R.drawable.yanablack, "₹500", "Bara Nimdih Chaibasa", "1 week", "01:26 pm", "25 Mar 2024"));
        rideModelArrayList.add(new RideModel(R.drawable.yanablack, "₹500", "Bara Nimdih Chaibasa", "1 week", "01:26 pm", "25 Mar 2024"));
        rideModelArrayList.add(new RideModel(R.drawable.yanablack, "₹500", "Bara Nimdih Chaibasa", "1 week", "01:26 pm", "25 Mar 2024"));

        // Add more ride models as needed...

        RecyclerRideHistoryAdapter adapter = new RecyclerRideHistoryAdapter(this, rideModelArrayList);
        recyclerView.setAdapter(adapter);

        // Apply insets to the root layout
        View rootView = findViewById(android.R.id.content);
        rootView.setOnApplyWindowInsetsListener((v, insets) -> {
            WindowInsets systemInsets = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                systemInsets = insets;
            }
            int statusBarHeight = systemInsets.getSystemWindowInsetTop();
            int navigationBarHeight = systemInsets.getSystemWindowInsetBottom();
            v.setPadding(0, statusBarHeight, 0, navigationBarHeight);
            return insets.consumeSystemWindowInsets();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.menu_action_bar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId ==R.id.opt_new)
        {
            Toast.makeText(this, "Created New File", Toast.LENGTH_SHORT).show();
        }
        else if(itemId==R.id.opt_new2)
        {
            Toast.makeText(this, "Support", Toast.LENGTH_SHORT).show();
        }
        else if(itemId == android.R.id.home){
            // backbutton
            Toast.makeText(this, "back button", Toast.LENGTH_SHORT).show();

        }
        return super.onOptionsItemSelected(item);
    }
}
