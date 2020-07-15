package com.example.time2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

/**
 * Activity for loading the user dashboard
 *
 * This activity is used to display the user's goal summary
 */
public class DashboardActivity extends AppCompatActivity{
    BottomNavigationView bottomNavigation;
    CardView cardView;
    FirebaseFirestore fStore;
    String TAG = "Dashboard Activity:";
    private CollectionReference userGoals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Initialize UI elements
        bottomNavigation = findViewById(R.id.bottom_navigation);
        cardView = findViewById(R.id.firstGoal);

        // Initialize FireStore
        //fStore = FirebaseFirestore.getInstance();
        //userGoals.getFirestore().collection("User_Goals");

        // Bottom Navigation Implementation
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                // SWITCH to decide which case/fragment to go to
                switch (item.getItemId()) {
                    case R.id.navigation_dashboard:
                        startActivity(new Intent(getApplicationContext(),DashboardActivity.class));
                        return true;
                    case R.id.navigation_settings:
                        startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                        return true;
                    case R.id.navigation_goal:
                        startActivity(new Intent(getApplicationContext(),AddGoalActivity.class));
                        return true;
                }
                return false;
            }
        });
    }
}