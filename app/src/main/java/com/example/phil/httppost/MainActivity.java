package com.example.phil.httppost;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.phil.httppost.data.model.Login;
import com.example.phil.httppost.data.model.User;
import com.example.phil.httppost.data.remote.ApiUtils;
import com.example.phil.httppost.data.remote.GoodJobService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private GoodJobService goodJobService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button login = (Button) findViewById(R.id.login);
        goodJobService = ApiUtils.getAPIService();

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                EditText emailText = (EditText) findViewById(R.id.emailText);
                String email = emailText.getText().toString();
                EditText passText = (EditText) findViewById(R.id.passwordText);
                String password = passText.getText().toString();
                doLogin(email, password );
            }
        });
    }

    public void doLogin(String email, String password){
        goodJobService.login(new Login(email, password)).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    System.out.println("okay\n" + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println("FAIL" + t.toString());
            }
        });
    }
}
