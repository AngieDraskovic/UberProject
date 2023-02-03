package com.example.easygo.passenger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
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

        TextView headerAnotherUser = findViewById(R.id.chatHeaderUser);
        ImageView headerUserIcon = findViewById(R.id.chatHeaderProfileIcon);

        TextView txtMyMessage;
        TextView txtAnotherMessage;
        rootLinear = findViewById(R.id.rootLinear);

        String[] messages = getIntent().getStringArrayExtra("textMessages");
        String[] senders = getIntent().getStringArrayExtra("senders");
        String anotherUser = getIntent().getStringExtra("anotherUser");
        int icon = getIntent().getIntExtra("icon", R.drawable.profile_default);

        headerAnotherUser.setText(anotherUser);
        headerUserIcon.setImageResource(icon);

        for (int i = 0; i < messages.length; i++) {
            if (!senders[i].equals(anotherUser)) {
                txtMyMessage = createMyMessage(messages[i]);
                rootLinear.addView(txtMyMessage);
            }
            else {
                txtAnotherMessage = createAnotherMessage(messages[i], senders[i]);
                rootLinear.addView(txtAnotherMessage);
            }
//            createSpace();
        }

    }


    /*
        Kreiramo TextView ako smo mi poslali poruku
    */
    @SuppressLint("ResourceAsColor")
    private TextView createMyMessage(String text) {
        TextView txtAnother = new TextView(MessageDetailActivity.this);
        txtAnother.setText(text);
        txtAnother.setTextColor(getResources().getColor(R.color.black));

        // Set background drawable with rounded corners
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setColor(getResources().getColor(R.color.my_message_color));
        shape.setCornerRadius(15);
        txtAnother.setBackground(shape);

        // Add padding
        int padding = (int) getResources().getDimension(R.dimen.text_padding);
        txtAnother.setPadding(padding, padding, padding, padding);

        // Add margins
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        int margin = (int) getResources().getDimension(R.dimen.text_margin);
        params.setMargins(0, 0, margin, margin);
        params.gravity = Gravity.END;
        txtAnother.setLayoutParams(params);

        return txtAnother;
    }

    // Samo prostor prazan izmedju poruka
    private TextView createSpace(){
        TextView txtAnother = new TextView(MessageDetailActivity.this);
        txtAnother.setText("    ");

        return txtAnother;
    }


    /*
        Kreiramo TextView ako je poruku poslao drugi korisnik
    */
    @SuppressLint("ResourceAsColor")
    private TextView createAnotherMessage(String text, String sender) {
        TextView txtAnother = new TextView(MessageDetailActivity.this);
        txtAnother.setText(text);
        txtAnother.setTextColor(getResources().getColor(R.color.black));

        // Set background drawable with rounded corners
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setColor(getResources().getColor(R.color.another_message_color));
        shape.setCornerRadius(15);
        txtAnother.setBackground(shape);

        // Add padding
        int padding = (int) getResources().getDimension(R.dimen.text_padding);
        txtAnother.setPadding(padding, padding, padding, padding);

        // Add margins
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        int margin = (int) getResources().getDimension(R.dimen.text_margin);
        params.setMargins(margin, 0, 0, margin);
        params.gravity = Gravity.START;
        txtAnother.setLayoutParams(params);

        return txtAnother;
    }

}