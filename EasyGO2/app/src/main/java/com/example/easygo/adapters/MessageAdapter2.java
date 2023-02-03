package com.example.easygo.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.easygo.R;
import com.example.easygo.model.Conversation;
import com.example.easygo.model.enumerations.MessaggeType;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MessageAdapter2 extends BaseAdapter {
    private Activity activity;
    private ArrayList<Conversation> conversations;

    public MessageAdapter2(Activity activity, ArrayList<Conversation> conversations){
        this.activity = activity;
        this.conversations = conversations;
    }

    @Override
    public int getCount() { return conversations.size();/* MockupMessages.getCurrUserMessages(LoggedIn.getUser()).size(); */ }

    @Override
    public Conversation getItem(int index) { return conversations.get(index); /* MockupMessages.getCurrUserMessages(LoggedIn.getUser()).get(index); */ }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        Conversation conversation = this.conversations.get(position);
//        Conversation conversation = MockupMessages.getCurrUserMessages(LoggedIn.getUser()).get(position);

        if (convertView == null)
            vi = activity.getLayoutInflater().inflate(R.layout.message_list_item, null);

        ImageView senderProfileIcon = vi.findViewById(R.id.senderProfileIcon);
        TextView txtSenderName = vi.findViewById(R.id.txtSenderName);
        TextView txtMessageContent = vi.findViewById(R.id.txtMessageContent);
        TextView txtMessageTime = vi.findViewById(R.id.messageTime);
        TextView txtMessageDate = vi.findViewById(R.id.messageDate);

        senderProfileIcon.setImageResource(conversation.getAnotherUser().getProfilePic());
        txtSenderName.setText(conversation.getAnotherUser().toString());
        txtMessageContent.setText(conversation.getLastMessage());

        setMessageColor(txtMessageContent, txtSenderName, conversation.getLastMessageObject().getType());

        LocalDateTime date = conversation.getLastMessageObject().getTime();
        
        String[] splitted = date.toString().split("T");
        String[] timeString = splitted[1].split(":");
        String hours = timeString[0] + ":" + timeString[1];
        txtMessageTime.setText(hours);
        txtMessageDate.setText(splitted[0]);


        return vi;
    }

    private void setMessageColor(TextView txtMessageContent, TextView txtSenderName, MessaggeType messaggeType) {
        if (messaggeType.equals(MessaggeType.PANIC)) {
            txtMessageContent.setTextColor(Color.parseColor("#FF0000"));
        } else {
            txtMessageContent.setTextColor(Color.parseColor("#018786"));
        }
    }

}
