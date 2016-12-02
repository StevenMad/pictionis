package com.dev.mypictionis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dev.mypictionis.com.dev.mypictionis.db.Message;

import java.util.ArrayList;

/**
 * Created by Madhow on 15/11/2016.
 */

public class ChatObject {

    int id;
    ArrayList<Message> messages;
    int messageId;

    public ChatObject(int gameId) {
        this.id = gameId;
        messages = new ArrayList<Message>();
        messageId = 0;
    }

    void addMessage(Message message)
    {
        messages.add(messageId,message);
        messageId++;
    }

}


//public class ChatActivity extends AppCompatActivity {
//    private ListView lv;
//    private ArrayAdapter<String> adapter;
//    @Override
//    protected void onCreate (Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_game);
//
//        adapter = new ArrayAdapter<String>(this,R.layout.activity_game);
//
//        lv = (ListView)findViewById(R.id.listView);
//        lv.setAdapter(adapter);
//        adapter.add(ChatObject.messageId);
//    }
//}