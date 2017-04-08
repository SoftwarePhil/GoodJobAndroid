package com.example.phil.httppost;

import android.app.ActionBar;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.phil.httppost.data.model.ChatRequest;
import com.example.phil.httppost.data.model.Chat;
import com.example.phil.httppost.data.model.Message;
import com.example.phil.httppost.data.model.SendMessage;
import com.example.phil.httppost.data.model.User;
import com.example.phil.httppost.data.remote.ApiUtils;
import com.example.phil.httppost.data.remote.GoodJobService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import org.phoenixframework.channels.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;

public class ChatActivity extends AppCompatActivity {
    private GoodJobService goodJobService;
    String email;
    String chatName;
    String userName;
    TextView messageBox;



    Socket socket;
    Socket socket2;

    Channel channel;

    ArrayList<Message> messages;
    Gson gson = new Gson();
    LinearLayout lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        goodJobService = ApiUtils.getAPIService();

        SharedPreferences pref = ChatActivity.this.getSharedPreferences("USER", MainActivity.MODE_PRIVATE);
        String json = pref.getString("user", null);
        gson = new Gson();
        User user = gson.fromJson(json, User.class);
        userName = user.getName();
        email = user.getEmail();

        chatName = getIntent().getStringExtra("chat_name");

        fetchChat();

        Button send = (Button) findViewById(R.id.send);
        messageBox = (EditText) findViewById(R.id.sendBox);


        try {
            socket = new Socket("ws:"+ApiUtils.BASE.toString()+"socket/websocket");
            socket.connect();
            channel = socket.chan("chat:"+ user.getEmail().toString() + "&&"+ chatName, null);

            channel.join()
                    .receive("ok", new IMessageCallback() {
                        @Override
                        public void onMessage(Envelope envelope) {
                            System.out.println("IGNORE");
                        }
                    });

            channel.on("new_message", new IMessageCallback() {
                @Override
                public void onMessage(Envelope envelope) {
                    System.out.println(envelope.getPayload().toString());

                    final Message m = gson.fromJson(envelope.getPayload().toString(), Message.class);
                    updateMessages(m);
                }
            });




        } catch (IOException e) {
            System.out.println("error connecting to chat");
            e.printStackTrace();
    }

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage(messageBox.getText().toString());
                messageBox.setText("");
            }
        });
    }

    public void fetchChat(){
        goodJobService.getChat(new ChatRequest(email, chatName)).enqueue(new Callback<Chat>() {
            @Override
            public void onResponse(Call<Chat> call, Response<Chat> response) {
                if (response.isSuccessful()) {
                    createChat(response.body().getMessages());
                }
            }

            @Override
            public void onFailure(Call<Chat> call, Throwable t) {
                System.out.println("FAIL" + t.toString());
            }
        });
    }

    public void createChat(ArrayList<Message> messages){
        this.messages = messages;
        lm = (LinearLayout) findViewById(R.id.chatLay);
        lm.removeAllViewsInLayout();

        // create the layout params that will be used to define how your
        // button will be displayed
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);


        for(Message message: messages)
        {
            // Create LinearLayout
            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.HORIZONTAL);

            // Create TextView
            TextView text = new TextView(this);
            text.setText(message.getSender_name() + ": " + message.getContent());
            ll.addView(text);

            lm.addView(ll);
        }
    }

    public void sendMessageSocket(final String message){
        ObjectNode node = new ObjectNode(JsonNodeFactory.instance)
                .put("sender", email)
                .put("sender_name", userName)
                .put("content", message);


        try{
            channel.push("chat_send", node);
        }
        catch (Exception e){
            Log.e("message failed to send",  message);
    }
    }

    public void sendMessage(final String message){
        //channel.push("new_message", new_message);
        goodJobService.sendMessage(new SendMessage(email, userName, email, chatName, message)).enqueue(new Callback<Chat>() {
            @Override
            public void onResponse(Call<Chat> call, Response<Chat> response) {
                if (response.isSuccessful()) {
                  System.out.println("message send");
                }
            }

            @Override
            public void onFailure(Call<Chat> call, Throwable t) {
                System.out.println("FAIL" + t.toString());
            }
        });
    }

    public void updateMessages(Message message){
        final LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.HORIZONTAL);

        // Create TextView
        TextView text = new TextView(this);
        text.setText(message.getSender_name() + ": " + message.getContent());
        ll.addView(text);

        lm.post(new Runnable() {
            @Override
            public void run() {
                lm.addView(ll); }
        });
    }


}
