package com.example.phil.httppost;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.phil.httppost.data.model.Wrap;
import com.example.phil.httppost.data.model.Create;
import com.example.phil.httppost.data.model.User;
import com.example.phil.httppost.data.remote.ApiUtils;
import com.example.phil.httppost.data.remote.GoodJobService;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {

    private GoodJobService goodJobService;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        goodJobService = ApiUtils.getAPIService();

        Button create = (Button) findViewById(R.id.submit);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit(view);
            }
        });
    }

    public void submit(View view) {
        EditText nameText = (EditText) findViewById(R.id.nameText);
        String name = nameText.getText().toString();

        EditText emailText = (EditText) findViewById(R.id.emailText);
        String email = emailText.getText().toString();

        EditText passwordText = (EditText) findViewById(R.id.passwordText);
        String password = passwordText.getText().toString();

        EditText bioText = (EditText) findViewById(R.id.bioText);
        String bio = bioText.getText().toString();

        EditText resumeText = (EditText) findViewById(R.id.resumeText);
        String resume = resumeText.getText().toString();

        EditText tagsText = (EditText) findViewById(R.id.tagsText);
        String tagsStr = tagsText.getText().toString();
        String[] tags = tagsStr.split(", ");

        EditText distanceText = (EditText) findViewById(R.id.distanceText);
        String distanceStr = distanceText.getText().toString();
        int distance = Integer.parseInt(distanceStr);

        String picture = "default";

        doSignUp(name, email, password, bio, resume, tags, distance, picture);
    }

    private void doSignUp(String name, String email, String password, String bio, String resume, String[] tags, int distance, String picture) {
        goodJobService.create(new Wrap(new Create(name, email, password, bio, resume, tags, distance, picture))).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    System.out.println("okay user created\n" + response.body().toString());
                    finishActivity(420);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println("FAIL" + t.toString());
            }
        });
    }

}
