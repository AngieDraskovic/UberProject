package com.example.easygo.passenger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.easygo.R;
import com.example.easygo.adapters.MessageAdapter2;

public class PassengerInboxActivity extends AppCompatActivity {


    private DrawerLayout drawerLayout;
    private ListView mDrawerList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_inbox);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerList = findViewById(R.id.navList);
        drawerLayout = findViewById(R.id.mesaggesDrawerView);
        MessageAdapter2 messageAdapter2 = new MessageAdapter2(this);
        mDrawerList.setAdapter(messageAdapter2);
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
                startActivity(new Intent(PassengerInboxActivity.this, PassengerAccountActivity.class));
                break;
            case
                    R.id.history:
                startActivity(new Intent(PassengerInboxActivity.this, PassengerRideHistoryActivity.class));
                break;
            case
                    R.id.home:
                startActivity(new Intent(PassengerInboxActivity.this, PassengerMainActivity.class));
                break;
            case
                    R.id.inbox:
                startActivity(new Intent(PassengerInboxActivity.this, PassengerInboxActivity.class));
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
}