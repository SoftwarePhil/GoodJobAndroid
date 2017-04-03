package com.example.phil.httppost;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.phil.httppost.data.model.User;
import com.example.phil.httppost.data.remote.ApiUtils;
import com.example.phil.httppost.data.remote.GoodJobService;
import com.example.phil.httppost.data.model.Email;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobFeed extends AppCompatActivity {
    String email;
    ArrayList<String> jobList = new ArrayList<String>();
    TextView jobName;
    private GoodJobService goodJobService;
    static final String NO_JOBS_MSG = "no more jobs!";
    private String currentJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_feed);
        goodJobService = ApiUtils.getAPIService();

        //pull user from shared pref
        SharedPreferences pref = JobFeed.this.getSharedPreferences("USER", MainActivity.MODE_PRIVATE);
        String json = pref.getString("user", null);
        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);
        email = user.getEmail();

        fetchJobs();
        jobName = (TextView) findViewById(R.id.job);

        Button like = (Button) findViewById(R.id.like);
        //when like button is clicked
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                likeJob();
            }
        });

        Button details = (Button) findViewById(R.id.details);
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jobDetails(view);
            }
        });

        Button chats = (Button) findViewById(R.id.chats);
        chats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToChats(view);
            }
        });
    }

    public void likeJob(){

        setAndRemove();
        //like job will go here!!
    }

    public void jobDetails(View view){
        if(!currentJob.equals(NO_JOBS_MSG)){
            Intent intent = new Intent(JobFeed.this, JobView.class);
            intent.putExtra("job", currentJob);
            startActivity(intent);
        }
    }

    public void fetchJobs(){
        goodJobService.jobList(new Email(email)).enqueue(new Callback<ArrayList>() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
                    jobList = (ArrayList)response.body();

                    setAndRemove();
                }
            }

            @Override
            public void onFailure(Call<ArrayList> call, Throwable t) {
                System.out.println("FAIL" + t.toString());
            }
        });
    }

    private void setAndRemove(){
        if(!jobList.isEmpty()){
            currentJob = jobList.get(0);
            String[] jobView = currentJob.split("&");
            jobName.setText(jobView[0] + " ~ " + jobView[1]);
            jobList.remove(0);
        }
        else{
            currentJob = NO_JOBS_MSG;
            jobName.setText(NO_JOBS_MSG);
        }
    }

    public void goToChats(View view){
        Intent intent = new Intent(JobFeed.this, ChatListActivity.class);
        startActivity(intent);
    }

}
