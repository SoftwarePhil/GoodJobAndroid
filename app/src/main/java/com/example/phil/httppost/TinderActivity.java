package com.example.phil.httppost;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.phil.httppost.data.model.GoodResponse;
import com.example.phil.httppost.data.model.JobPreview;
import com.example.phil.httppost.data.model.LikeRequest;
import com.example.phil.httppost.data.model.User;
import com.example.phil.httppost.data.remote.ApiUtils;
import com.example.phil.httppost.data.remote.GoodJobService;
import com.google.gson.Gson;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import java.util.ArrayList;
import com.example.phil.httppost.data.model.Email;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by daj513 on 3/27/2017.
 */

public class TinderActivity extends AppCompatActivity{

    private SwipePlaceHolderView mSwipeView;
    private Context mContext;
    private ArrayList<JobPreview> jobs = new ArrayList<JobPreview>();
    public GoodJobService goodJobService;
    public String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_tinder);
        goodJobService = ApiUtils.getAPIService();

        ImageButton msgButton = (ImageButton) findViewById(R.id.msgbtn);

        msgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(TinderActivity.this, ChatListActivity.class);
                startActivity(myIntent);
            }
        });

        SharedPreferences pref = TinderActivity.this.getSharedPreferences("USER", MainActivity.MODE_PRIVATE);
        String json = pref.getString("user", null);
        Gson gson = new Gson();
        User user = gson.fromJson(json, User.class);
        email = user.getEmail();

        fetchJobs();


        mSwipeView = (SwipePlaceHolderView)findViewById(R.id.swipeView);
        mContext = getApplicationContext();

        mSwipeView.getBuilder()
                .setDisplayViewCount(3)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.tinder_swipe_in_msg_view)
                        .setSwipeOutMsgLayoutId(R.layout.tinder_swipe_out_msg_view));



        findViewById(R.id.rejectBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(false);
            }
        });

        findViewById(R.id.acceptBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(true);
            }
        });


    }

    public void drawCards(ArrayList<JobPreview> jobs){
        for(JobPreview job : jobs){
            mSwipeView.addView(new JobCard(mContext, job, mSwipeView, goodJobService, email));
        }
    }


    public void fetchJobs(){
        goodJobService.jobList(new Email(email)).enqueue(new Callback<ArrayList<JobPreview>>() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
                    System.out.println("success");
                    jobs = (ArrayList<JobPreview>)response.body();

                    for(JobPreview j : jobs){
                        System.out.println(j.getName());
                    }

                    drawCards(jobs);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<JobPreview>> call, Throwable t) {
                System.out.println("FAIL" + t.toString());
            }
        });
    }

    public void sendLike(String job, String choice){

        goodJobService.likeJob(new LikeRequest(job, email, choice)).enqueue(new Callback<GoodResponse>() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
                    System.out.println("job liked");
                }
            }

            @Override
            public void onFailure(Call<GoodResponse> call, Throwable t) {
                System.out.println("FAIL" + t.toString());
            }
        });
    }

    /* on card click we need to make an activity with the job
    public void jobDetails(View view){
        if(!currentJob.equals(NO_JOBS_MSG)){
            Intent intent = new Intent(JobFeed.this, JobView.class);
            intent.putExtra("job", currentJob);
            startActivity(intent);
        }
    }
        */
}
