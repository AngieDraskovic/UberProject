package com.example.easygo.passenger;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.easygo.FragmentTransition;
import com.example.easygo.LoggedIn;
import com.example.easygo.R;
import com.example.easygo.adapters.MessageAdapter2;
import com.example.easygo.UserLoginActivity;
import com.example.easygo.dto.UserDTO;
import com.example.easygo.mockup.MockupMessages;
import com.example.easygo.model.Conversation;
import com.example.easygo.model.Message;
import com.example.easygo.model.Ride;
import com.example.easygo.model.users.Passenger;
import com.example.easygo.service.ServiceUtilis;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PassengerInboxActivity extends AppCompatActivity {

//    private DrawerLayout drawerLayout;
//    private ListView mDrawerList;

    private List<Message> messages;
    private Passenger passenger;
    private ArrayList<Conversation> conversations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_inbox);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getPassenger();




//        mDrawerList = findViewById(R.id.navList);
//        drawerLayout = findViewById(R.id.mesaggesDrawerView);
//        MessageAdapter2 messageAdapter2 = new MessageAdapter2(this);
//        mDrawerList.setAdapter(messageAdapter2);
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
            case
                    R.id.logout:
                startActivity(new Intent(PassengerInboxActivity.this, UserLoginActivity.class));
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



    public void getPassenger(){
        SharedPreferences preferences = getSharedPreferences("preference_file_name", MODE_PRIVATE);
        int id = preferences.getInt("p_id", 0);
        Call<UserDTO> call = ServiceUtilis.userService.getPassenger(id);
        call.enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    passenger = new Passenger(response.body());
                    getMessages(passenger);
                }
            }

            @Override
            public void onFailure(Call<UserDTO> call, Throwable t) {

            }
        });
    }

    public void getMessages(Passenger passenger) {
        Call<List<Message>> call = ServiceUtilis.messageService.getAllMessages();
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    messages = response.body();
                    conversations = MockupMessages.getCurrUserMessages(passenger, messages);

                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("conversations", conversations);
                    FragmentTransition.to(MessagesFragment.newInstance(bundle), PassengerInboxActivity.this, false, R.id.messagesContent);

                    System.out.println("ISPIS: Prosao all messages");
                }
            }
            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                System.out.println("ISPIS: Failovano finish ride");
            }
        });
    }


}