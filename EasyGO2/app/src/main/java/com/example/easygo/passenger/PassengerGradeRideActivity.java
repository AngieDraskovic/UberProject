package com.example.easygo.passenger;

import androidx.appcompat.app.AppCompatActivity;
import com.example.easygo.R;
import com.example.easygo.model.Review;
import com.example.easygo.model.Ride;
import com.example.easygo.service.ServiceUtilis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PassengerGradeRideActivity extends AppCompatActivity {

    private EditText driverGradeEditText;
    private EditText vehicleGradeEditText;
    private EditText commentEditText;
    private Button confirmGradeButton;

    private int rideId;
    private int passengerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_grade_ride);

        Intent intent = getIntent();
        rideId = intent.getIntExtra("ride", 0);
        passengerId = intent.getIntExtra("passenger", 0);
    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        confirmGradeButton = findViewById(R.id.confirmGradeBtn);
        confirmGradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    driverGradeEditText = findViewById(R.id.driverGrade);
                    vehicleGradeEditText = findViewById(R.id.vehicleGrade);
                    commentEditText = findViewById(R.id.gradeComment);

                    int driverGrade = Integer.parseInt(driverGradeEditText.getText().toString());
                    int vehicleGrade = Integer.parseInt(vehicleGradeEditText.getText().toString());
                    String comment = commentEditText.getText().toString();

                    Review review = new Review(driverGrade, vehicleGrade, comment);
                    createRewiew(review);
                } catch (Exception e) {
                    Toast.makeText(PassengerGradeRideActivity.this, "Grade numbers must be 1-5", Toast.LENGTH_SHORT).show();
                }

            }
        });

        TextView backArrow = (TextView)findViewById(R.id.back_arrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PassengerGradeRideActivity.this, PassengerMainActivity.class));
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public void createRewiew(Review review) {
        Call<Review> call = ServiceUtilis.reviewService.createReview(review, rideId, passengerId);
        call.enqueue(new Callback<Review>() {
            @Override
            public void onResponse(Call<Review> call, Response<Review> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(PassengerGradeRideActivity.this,"Reviewed successfully!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Review> call, Throwable t) {
                Toast.makeText(PassengerGradeRideActivity.this,"Failovao review", Toast.LENGTH_SHORT).show();
            }
        });

    }

}