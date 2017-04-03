package com.example.phil.httppost;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.phil.httppost.data.model.Chat;
import com.example.phil.httppost.data.model.ChatRequest;
import com.example.phil.httppost.data.model.Job;
import com.example.phil.httppost.data.model.JobRequest;
import com.example.phil.httppost.data.remote.ApiUtils;
import com.example.phil.httppost.data.remote.GoodJobService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobView extends AppCompatActivity {

    String jobName;
    TextView jobNameText;
    TextView companyText;
    TextView descriptionText;
    TextView postDateText;
    TextView salaryRangeText;
    TextView employmentTypeText;
    TextView locationText;
    TextView tagsText;

    private GoodJobService goodJobService;
    String postDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_view);
        goodJobService = ApiUtils.getAPIService();

        jobNameText = (TextView) findViewById(R.id.jobName);
        companyText = (TextView) findViewById(R.id.company);
        descriptionText = (TextView) findViewById(R.id.description);
        salaryRangeText = (TextView) findViewById(R.id.salary);
        employmentTypeText = (TextView) findViewById(R.id.employment_type);
        postDateText = (TextView) findViewById(R.id.post_date);
        locationText = (TextView) findViewById(R.id.location);
        tagsText = (TextView) findViewById(R.id.tags);

        jobName = getIntent().getStringExtra("job");

        fetchJob();
    /*
        jobNameText.setText(job.getName());
        companyText.setText(job.getCompany());
        descriptionText.setText(job.getDesciption());
        salaryRangeText.setText(job.getSalary_range());
        employmentTypeText.setText(job.getEmployment_type());
        postDateText.setText(postDate);
        locationText.setText(job.getLocation());

        //TODO: Make this better, like have each tag be separated by a comma
        String allTags = "";
        for(String s: job.getTags()){
            allTags = allTags + " " + s;
        }
        tagsText.setText(allTags);*/
    }

    public void fetchJob(){
        goodJobService.getJob(new JobRequest(jobName)).enqueue(new Callback<Job>() {
            @Override
            public void onResponse(Call<Job> call, Response<Job> response) {
                if (response.isSuccessful()) {
                    System.out.println("okay\n" + response.body().getName());
                    fillJob(response.body());
                }
            }

            @Override
            public void onFailure(Call<Job> call, Throwable t) {
                System.out.println("FAIL" + t.toString());
            }
        });;
    }

    public void fillJob(Job job){
        jobNameText.setText(job.getName());
        companyText.setText(job.getCompany());
        descriptionText.setText(job.getDesciption());
        salaryRangeText.setText(job.getSalary_range());
        employmentTypeText.setText(job.getEmployment_type());
        postDateText.setText(postDate);
        locationText.setText(job.getLocation());

        //TODO: Make this better, like have each tag be separated by a comma
        String allTags = "";
        for(String s: job.getTags()){
            allTags = allTags + " " + s;
        }
        tagsText.setText(allTags);
    }

}
