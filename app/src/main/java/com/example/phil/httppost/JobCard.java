package com.example.phil.httppost;

import android.content.Context;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.phil.httppost.data.model.Company;
import com.example.phil.httppost.data.model.CompanyRequest;
import com.example.phil.httppost.data.model.GoodResponse;
import com.example.phil.httppost.data.model.Job;
import com.example.phil.httppost.data.model.JobPreview;
import com.example.phil.httppost.data.model.JobRequest;
import com.example.phil.httppost.data.model.LikeRequest;
import com.example.phil.httppost.data.remote.GoodJobService;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState;
import com.mindorks.placeholderview.annotations.swipe.SwipeIn;
import com.mindorks.placeholderview.annotations.swipe.SwipeInState;
import com.mindorks.placeholderview.annotations.swipe.SwipeOut;
import com.mindorks.placeholderview.annotations.swipe.SwipeOutState;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by daj513 on 3/27/2017.
 */
@Layout(R.layout.tinder_card_view)
public class JobCard {

    @View(R.id.profileImageView)
    private ImageView profileImageView;

    @View(R.id.nameAgeTxt)
    private TextView nameAgeTxt;

    @View(R.id.locationNameTxt)
    private TextView locationNameTxt;

    private JobPreview jobPreview;
    private Context mContext;
    private SwipePlaceHolderView mSwipeView;
    private GoodJobService goodJobService;
    private String email;
    private Company company;

    public JobCard(Context context, JobPreview job, SwipePlaceHolderView swipeView, GoodJobService service, String email) {
        mContext = context;
        jobPreview = job;
        mSwipeView = swipeView;
        this.email = email;
        this.goodJobService = service;
    }

    @Resolve
    private void onResolved(){
        getCompany();
        nameAgeTxt.setText(jobPreview.getName());
        locationNameTxt.setText(jobPreview.getCompany());
    }

    @SwipeOut
    private void onSwipedOut(){
        Log.d("EVENT", "onSwipedOut");
        mSwipeView.addView(this);
        System.out.println("sending pass");
        sendLike("pass");
    }

    @SwipeCancelState
    private void onSwipeCancelState(){
        Log.d("EVENT", "onSwipeCancelState");
    }

    @SwipeIn
    private void onSwipeIn(){
        System.out.println("sending like");
        sendLike("like");
        Log.d("EVENT", "onSwipedIn");
    }


    @SwipeInState
    private void onSwipeInState(){
        //Log.d("EVENT", "onSwipeInState");
    }

    @SwipeOutState
    private void onSwipeOutState(){
        //Log.d("EVENT", "onSwipeOutState");
    }

    public void sendLike(String choice){

        goodJobService.likeJob(new LikeRequest(jobPreview.getId(), email, choice)).enqueue(new Callback<GoodResponse>() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
                    System.out.println("job liked/passed");
                }
            }

            @Override
            public void onFailure(Call<GoodResponse> call, Throwable t) {
                System.out.println("FAIL" + t.toString());
            }
        });
    }

    public void getCompany(){

        goodJobService.getCompany(new CompanyRequest(jobPreview.getName())).enqueue(new Callback<Company>() {
            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
                    String raw =  response.raw().body().toString();
                    company = (Company)response.body();
                    System.out.println(company.getLogo());

                    final String encodedString = company.getLogo();
                    final String pureBase64Encoded = encodedString.substring(encodedString.indexOf(",")  + 1);
                    final byte[] decodedBytes = Base64.decode(pureBase64Encoded, Base64.DEFAULT);
                    Glide.with(mContext).load(decodedBytes).into(profileImageView);
                    //Glide.with(CaptchaFragment.this).load(decodedBytes).crossFade().fitCenter().into(mCatpchaImageView);
                }
            }

            @Override
            public void onFailure(Call<Company> call, Throwable t) {
                System.out.println("FAIL" + t.toString());
            }
        });
    }

}
