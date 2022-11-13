package com.example.easygo.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.easygo.R;
import com.example.easygo.mockup.MockupMessages;
import com.example.easygo.model.Message;

import org.w3c.dom.Text;

import java.util.concurrent.BrokenBarrierException;

public class MessageAdapter2 extends BaseAdapter
{
    private Activity activity;

    public MessageAdapter2(Activity activity){

        this.activity = activity;
    }

    @Override
    public int getCount(){
        return MockupMessages.getMessages().size();

    }

    @Override
    public Message getItem(int index){
        return MockupMessages.getMessages().get(index);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        Message message = MockupMessages.getMessages().get(position);

        if(convertView==null)
            vi = activity.getLayoutInflater().inflate(R.layout.item_container_messagge, null);

        TextView name = (TextView)vi.findViewById(R.id.textName);
        TextView email = (TextView)vi.findViewById(R.id.textEmail);
        ImageView image = (ImageView)vi.findViewById(R.id.imageProfile);


        name.setText(message.getDeliverer().getEmail());
        email.setText(message.getText());

        image.setImageResource(-1);
        return vi;



    }
}
