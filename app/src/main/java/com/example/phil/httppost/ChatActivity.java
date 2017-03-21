package com.example.phil.httppost;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.phil.httppost.data.model.ChatRequest;
import com.example.phil.httppost.data.model.Chat;
import com.example.phil.httppost.data.model.Message;
import com.example.phil.httppost.data.remote.ApiUtils;
import com.example.phil.httppost.data.remote.GoodJobService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity {
    private GoodJobService goodJobService;
    String email;
    String chatName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        goodJobService = ApiUtils.getAPIService();

        email = getIntent().getStringExtra("email");
        chatName = getIntent().getStringExtra("chat_name");

        fetchChat();

        Button refresh = (Button) findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchChat();
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

    public void createChat(Message[] messages){
        final LinearLayout lm = (LinearLayout) findViewById(R.id.chatLay);
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
}
