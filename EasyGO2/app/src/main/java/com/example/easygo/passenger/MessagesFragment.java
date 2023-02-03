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

    protected ArrayList<Conversation> conversations;

    public MessagesFragment() {

    }

    public static MessagesFragment newInstance(Bundle bundle) {
        MessagesFragment fragment = new MessagesFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            this.conversations = bundle.getParcelableArrayList("conversations");
        }

        MessageAdapter2 adapter = new MessageAdapter2(getActivity(), conversations);
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

        Conversation conversation = conversations.get(position);
//        Conversation conversation = new Conversation();

        Intent intent = new Intent(getActivity(), MessageDetailActivity.class);

        String[] textMessages = getTextFromConversation(conversation);      // ['Da li ostaje dogovor', 'da']
        String[] senders = getSendersFromConversation(conversation);        // ['me', vozac;]
        intent.putExtra("textMessages", textMessages);
        intent.putExtra("senders", senders);
        intent.putExtra("anotherUser", conversation.getAnotherUser().toString());
        intent.putExtra("icon", conversation.getAnotherUser().getProfilePic());
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