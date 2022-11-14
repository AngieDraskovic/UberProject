package com.example.easygo.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.easygo.R;
import com.example.easygo.mockup.MockupMessages;
import com.example.easygo.model.Conversation;
import com.example.easygo.model.Message;

import org.w3c.dom.Text;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.concurrent.BrokenBarrierException;

public class MessageAdapter2 extends BaseAdapter
{
    private Activity activity;

    public MessageAdapter2(Activity activity){
        this.activity = activity;
    }

    @Override
    public int getCount() { return MockupMessages.getCurrUserMessages().size(); }

    @Override
    public Conversation getItem(int index) { return MockupMessages.getCurrUserMessages().get(index); }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        Conversation conversation = MockupMessages.getCurrUserMessages().get(position);

        if (convertView == null)
            vi = activity.getLayoutInflater().inflate(R.layout.message_list_item, null);

        ImageView senderProfileIcon = vi.findViewById(R.id.senderProfileIcon);
        TextView txtSenderName = vi.findViewById(R.id.txtSenderName);
        TextView txtMessageContent = vi.findViewById(R.id.txtMessageContent);
        TextView txtMessageTime = vi.findViewById(R.id.messageTime);
        TextView txtMessageDate = vi.findViewById(R.id.messageDate);
        senderProfileIcon.setImageResource(conversation.getAnotherUser().getProfilePic());
        txtSenderName.setText(conversation.getAnotherUser().getName());
        txtMessageContent.setText(conversation.getLastMessage());

        LocalDateTime date = conversation.getLastMessageObject().getTime();

        String[] splitted = date.toString().split("T");


        String[] timeString = splitted[1].split(":");
        String hours = timeString[0] + ":" + timeString[1];
        txtMessageTime.setText(hours);
        txtMessageDate.setText(splitted[0]);


        return vi;
    }

}
