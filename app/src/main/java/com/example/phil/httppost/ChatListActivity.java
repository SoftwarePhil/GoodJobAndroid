package com.example.phil.httppost;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.phil.httppost.data.model.User;
import com.google.gson.Gson;

import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.app.ActionBar.LayoutParams;
import android.view.View;


public class ChatListActivity extends AppCompatActivity {
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        SharedPreferences pref = ChatListActivity.this.getSharedPreferences("USER", MainActivity.MODE_PRIVATE);
        String json = pref.getString("user", null);
        Gson gson = new Gson();
        user = gson.fromJson(json, User.class);

        final LinearLayout lm = (LinearLayout) findViewById(R.id.linearLay);

        // create the layout params that will be used to define how your
        // button will be displayed
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        for(String chat: user.getChatIds())
        {
            // Create LinearLayout
            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.HORIZONTAL);

            // Create TextView
            TextView text = new TextView(this);
            String[] chatName = chat.split("&");
            text.setText(chatName[0] + " ~ " + chatName[1]);
            ll.addView(text);


            // Create Button
            final Button btn = new Button(this);
            // Give button an ID
            btn.setHint(chat);
            btn.setText("go");
            // set the layoutParams on the button
            btn.setLayoutParams(params);

            //final int index = j;
            // Set click listener for button
            btn.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(ChatListActivity.this, ChatActivity.class);
                    intent.putExtra("chat_name", btn.getHint());
                    intent.putExtra("email", user.getEmail());
                    startActivity(intent);
                }
            });

            //Add button to LinearLayout
            ll.addView(btn);
            //Add button to LinearLayout defined in XML
            lm.addView(ll);
        }
    }
}
