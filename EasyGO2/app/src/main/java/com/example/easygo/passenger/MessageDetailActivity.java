package com.example.easygo.passenger;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.easygo.R;
import com.example.easygo.model.Message;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MessageDetailActivity extends AppCompatActivity {

    LinearLayout rootLinear;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);

        TextView txtMyMessage;
        TextView txtAnotherMessage;
        rootLinear = findViewById(R.id.rootLinear);

        String[] messages = getIntent().getStringArrayExtra("textMessages");
        String[] senders = getIntent().getStringArrayExtra("senders");

        for (int i = 0; i < messages.length; i++) {
            if (senders[i].equals("Me")) {
                txtMyMessage = createMyMessage(messages[i]);
                rootLinear.addView(txtMyMessage);
            }
            else {
                txtAnotherMessage = createAnotherMessage(messages[i], senders[i]);
                rootLinear.addView(txtAnotherMessage);
            }

        }

    }


    /*
        Kreiramo TextView ako smo mi poslali poruku
    */
    @SuppressLint("ResourceAsColor")
    private TextView createMyMessage(String text) {
        TextView txtAnother = new TextView(MessageDetailActivity.this);
        txtAnother.setText("Me: " + text);
        txtAnother.setTextColor(R.color.teal_700);
        return txtAnother;
    }

    /*
        Kreiramo TextView ako je poruku poslao drugi korisnik
    */
    @SuppressLint("ResourceAsColor")
    private TextView createAnotherMessage(String text, String sender) {
        TextView txtAnother = new TextView(MessageDetailActivity.this);
        txtAnother.setText(sender + ": " + text);
        txtAnother.setTextColor(R.color.black);
        return txtAnother;
    }

}