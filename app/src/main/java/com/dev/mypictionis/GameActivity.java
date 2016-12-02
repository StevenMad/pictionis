package com.dev.mypictionis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dev.mypictionis.com.dev.mypictionis.db.Message;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

// manager the activities of drawing and chat

public class GameActivity extends AppCompatActivity {

    private LinearLayout paintLayout;
    DrawingView drawingView;

    //chat init
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference chatRef = database.getReference("chat");
    TextView tv;
    EditText editText;
    Map<String,Message> messages ;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    static int id =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        drawingView =(DrawingView) findViewById(R.id.draw_screen);
        drawingView.setColor("#000000");

        //chat init
        tv = (TextView) findViewById(R.id.chat_message);
        //add a scrolling to the chat box
        tv.setMovementMethod(new ScrollingMovementMethod());
        editText = (EditText) findViewById(R.id.edit_message);
        chatRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                messages = new HashMap<String, Message>();
                int idref=1;
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Iterable<DataSnapshot> datas = dataSnapshot.getChildren();
                Iterator<DataSnapshot> ite = datas.iterator();
                String s ="";
                while(ite.hasNext())
                {
                    DataSnapshot data = ite.next();
                    Message message = data.getValue(Message.class);
                    messages.put(String.valueOf(idref),message);
                    s= message.getAuthor()+ " : "+message.getMessage()+"\n"+s;
                    idref++;
                }
                tv.setText(s);
                id = idref;
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }


    public void clearDraw(View view)
    {
        drawingView.clearDraw();
    }


    public void sendMessage(View view)
    {
        messages.put(String.valueOf(id),new Message(editText.getText().toString(),user.getEmail()));
        chatRef.setValue(messages);
        id++;
    }
}
