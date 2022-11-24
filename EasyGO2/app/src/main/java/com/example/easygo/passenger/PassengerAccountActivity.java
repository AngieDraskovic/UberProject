package com.example.easygo.passenger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easygo.LoggedIn;
import com.example.easygo.R;
import com.example.easygo.model.users.Passenger;
import com.example.easygo.UserLoginActivity;
public class PassengerAccountActivity extends AppCompatActivity {

    private Passenger passenger;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_account);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        View view = (View)findViewById(R.id.account);
        this.passenger = LoggedIn.getPassenger();
        LinearLayout editProfile = (LinearLayout) findViewById(R.id.userProfile);
        LinearLayout financialCard = (LinearLayout)findViewById(R.id.financialCard);
        LinearLayout reports = (LinearLayout)findViewById(R.id.reports);

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editProfile.setBackgroundColor(Color.parseColor("#574A46"));
               Intent intent = new Intent(PassengerAccountActivity.this,PassengerProfileActivity.class);
               startActivity(intent);
            }
        });

        financialCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                financialCard.setBackgroundColor(Color.parseColor("#574A46"));
                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.financialcard_popup, null);
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true;

                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                // dismiss the popup window when touched
                popupView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        popupWindow.dismiss();
                        financialCard.setBackgroundColor(Color.parseColor("#1B1A19"));
                        return true;
                    }
                });
            }
        });

        reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reports.setBackgroundColor(Color.parseColor("#574A46"));
                Intent intent = new Intent(PassengerAccountActivity.this,PassengerReportActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        setPassengerData();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.layout_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case
                    R.id.account:
                startActivity(new Intent(PassengerAccountActivity.this, PassengerAccountActivity.class));
                break;
            case
                    R.id.history:
                startActivity(new Intent(PassengerAccountActivity.this, PassengerRideHistoryActivity.class));
                break;
            case
                    R.id.home:
                startActivity(new Intent(PassengerAccountActivity.this, PassengerMainActivity.class));
                break;
            case
                    R.id.inbox:
                startActivity(new Intent(PassengerAccountActivity.this, PassengerInboxActivity.class));
                break;
            case
                    R.id.logout:
                startActivity(new Intent(PassengerAccountActivity.this, UserLoginActivity.class));
                break;
            default:
                break;
        }
        return true;
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


    private void setPassengerData() {
        String user = passenger.getName() + " " + passenger.getSurname();

        ((TextView) findViewById(R.id.txtUser)).setText(user);
        ((TextView) findViewById(R.id.txtEmail)).setText(passenger.getEmail());
        ((TextView) findViewById(R.id.txtPhone)).setText(passenger.getPhone());
        ((TextView) findViewById(R.id.txtAddress)).setText(passenger.getAddress());
        ((ImageView) findViewById(R.id.profileImg)).setImageResource(passenger.getProfilePic());
    }


}