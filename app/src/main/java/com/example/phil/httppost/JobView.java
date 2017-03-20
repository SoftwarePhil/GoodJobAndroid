package com.example.phil.httppost;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JobView extends AppCompatActivity {

    String jobName;
    TextView jobTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_view);

        jobTextName = (TextView) findViewById(R.id.jobName);
        jobName = getIntent().getStringExtra("job");

        jobTextName.setText(jobName);
    }
}
