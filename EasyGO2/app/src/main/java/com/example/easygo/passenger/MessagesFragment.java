package com.example.easygo.passenger;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.easygo.LoggedIn;
import com.example.easygo.R;
import com.example.easygo.adapters.MessageAdapter2;
import com.example.easygo.mockup.MockupMessages;
import com.example.easygo.model.Conversation;
import com.example.easygo.model.Message;
import com.example.easygo.model.users.User;

import java.util.ArrayList;


public class MessagesFragment extends ListFragment {

    public MessagesFragment() {

    }

    public static MessagesFragment newInstance() {
        MessagesFragment fragment = new MessagesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MessageAdapter2 adapter = new MessageAdapter2(getActivity());
        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        setHasOptionsMenu(true);

        return inflater.inflate(R.layout.fragment_messages, container, false);
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Conversation conversation = MockupMessages.getCurrUserMessages(LoggedIn.getUser()).get(position);

        Intent intent = new Intent(getActivity(), MessageDetailActivity.class);

        String[] textMessages = getTextFromConversation(conversation);      // ['Da li ostaje dogovor', 'da']
        String[] senders = getSendersFromConversation(conversation);        // ['me', vozac;]
        intent.putExtra("textMessages", textMessages);
        intent.putExtra("senders", senders);
        startActivity(intent);
    }

    private String[] getSendersFromConversation(Conversation conversation) {
        int size = conversation.getMessages().size();
        String[] senders = new String[size];
        for (int i = 0; i < size; i++) {
            User sender = conversation.getMessages().get(i).getSender();
            User me = LoggedIn.getUser();
            senders[i] = (sender.equals(me)) ? "Me" : sender.toString();
        }


        return senders;
    }

    private String[] getTextFromConversation(Conversation conversation) {
        int size = conversation.getMessages().size();
        String[] textMessages = new String[size];
        for (int i = 0; i < size; i++)
            textMessages[i] = conversation.getMessages().get(i).getText();

        return textMessages;
    }
}