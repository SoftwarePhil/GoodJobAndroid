package com.example.phil.httppost;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.phil.httppost.data.remote.ApiUtils;
import com.example.phil.httppost.data.remote.GoodJobService;
import com.example.phil.httppost.data.model.Email;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_feed);
        goodJobService = ApiUtils.getAPIService();

        SharedPreferences pref = JobFeed.this.getSharedPreferences("USER", MainActivity.MODE_PRIVATE);
        email = pref.getString("user", null);
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
        //when like button is clicked
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jobDetails(view);
            }
        });
    }

    public void likeJob(){
        String likedJob = jobName.getText().toString();

        if(jobList.size() > 0){
            jobName.setText(jobList.get(0));
            jobList.remove(0);
            //send liked job to GoodApi
        }
        else{
            jobName.setText(NO_JOBS_MSG);
        }
    }

    public void jobDetails(View view){
        String job = jobName.getText().toString();
        if(!job.equals(NO_JOBS_MSG)){
            Intent intent = new Intent(JobFeed.this, JobView.class);
            intent.putExtra("job", job);
            startActivity(intent);
        }
    }

    public void fetchJobs(){
        goodJobService.jobList(new Email(email)).enqueue(new Callback<ArrayList>() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
                    jobList = (ArrayList)response.body();

                    if(jobList.size() > 0){
                        jobName.setText(jobList.get(0));
                        jobList.remove(0);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList> call, Throwable t) {
                System.out.println("FAIL" + t.toString());
            }
        });
    };
}
