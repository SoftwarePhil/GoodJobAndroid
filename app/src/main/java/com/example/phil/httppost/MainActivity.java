package com.example.phil.httppost;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.phil.httppost.data.model.Login;
import com.example.phil.httppost.data.model.User;
import com.example.phil.httppost.data.remote.ApiUtils;
import com.example.phil.httppost.data.remote.GoodJobService;
import com.google.android.gms.common.api.GoogleApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.content.SharedPreferences;


public class MainActivity extends AppCompatActivity {

    private GoodJobService goodJobService;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button login = (Button) findViewById(R.id.login);
        Button signUp = (Button) findViewById(R.id.sign_up);
        goodJobService = ApiUtils.getAPIService();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText emailText = (EditText) findViewById(R.id.emailText);
                String email = emailText.getText().toString();
                EditText passText = (EditText) findViewById(R.id.passwordText);
                String password = passText.getText().toString();
                doLogin(email, password, view);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              nextScreen(view);
            }
        });

    }

    public void nextScreen(View view) {
        Intent intent = new Intent(this, SignUp.class);
        startActivityForResult(intent, 420);
    }


    public void doLogin(String email, String password, View view) {
        goodJobService.login(new Login(email, password)).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    System.out.println("okay\n" + response.body().toString());
                    SharedPreferences pref = MainActivity.this.getSharedPreferences("USER", MainActivity.MODE_PRIVATE);
                    pref.edit().putString("user", response.body().getEmail()).commit();
                    Intent intent = new Intent(MainActivity.this, JobFeed.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println("FAIL" + t.toString());
            }
        });
    }


}
