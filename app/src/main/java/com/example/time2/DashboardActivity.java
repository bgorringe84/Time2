package com.example.time2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import java.util.Objects;

/**
 * Activity for loading the user dashboard
 *
 * This activity is used to display the user's goal summary
 */
public class DashboardActivity extends AppCompatActivity{
    BottomNavigationView bottomNavigation;
    TextView goalTitle, goalCost;
    TextView displayName;
    TextView output;
    private RecyclerView fStoreList;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    String TAG = "Dashboard Activity:";
    String userId;
    double income, percentage, goal_cost, time;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Initialize UI elements
        bottomNavigation = findViewById(R.id.bottom_navigation);
        displayName = findViewById(R.id.welcome);
        goalCost = findViewById(R.id.goal_cost);
        goalTitle = findViewById(R.id.goal_title);
        output = findViewById(R.id.time);

        // Initialize FireStore
        fStore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();

        //Used shared pref to fetch display name
        sharedPref = getSharedPreferences("pref", MODE_PRIVATE);
        displayName.setText(String.format("Welcome %s!", sharedPref.getString("userName", "")), TextView.BufferType.EDITABLE);


        userId = fAuth.getCurrentUser().getUid();
        DocumentReference documentReference = fStore.collection("User_Goal").document(userId);

         // Displays Welcome message to include set displayName
         DocumentReference profileReference = fStore.collection("User_Pref").document(userId);

         profileReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
             @SuppressLint("SetTextI18n")
             @Override
             public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                 //displayName.setText("Welcome " + value.getString("name") + "!");
                 income = Double.parseDouble(Objects.requireNonNull(value.getString("income")));
                 percentage = Double.parseDouble(Objects.requireNonNull(value.getString("saving")));
             }
         });

        // Retrieve the data and set the data to local variables
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                goalTitle.setText(value.getString("title"));
                goalCost.setText("Cost: " + value.getString("cost"));
                goal_cost = Double.parseDouble(Objects.requireNonNull(value.getString("cost")));

                percentage /= 100f;
                time = (goal_cost / (income * percentage)) * 30f;

                // Convert to integer to lose decimal
                int result = (int)time;

                // Convert to string to display output
                output.setText(Integer.toString(result) + " days to reach goal");
            }
        });


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


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}