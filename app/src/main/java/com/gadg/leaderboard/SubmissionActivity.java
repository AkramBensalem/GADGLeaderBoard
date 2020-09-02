package com.gadg.leaderboard;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmissionActivity extends AppCompatActivity {

    // The base url to post the entities
    private static final String BASE_URL = "https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse";

    private AlertDialog alertDialog;
    private EditText mNameEditText, mLastNameEditText, mEmailEditText, mProjectLinkEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);

        mNameEditText = (EditText) findViewById(R.id.submit_first_name);
        mLastNameEditText = (EditText) findViewById(R.id.submit_last_name);
        mEmailEditText = (EditText) findViewById(R.id.submit_email);
        mProjectLinkEditText = (EditText) findViewById(R.id.submit_project);

        findViewById(R.id.submission_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show the confirmation dialog before take an action
                AlertDialog.Builder builder = new AlertDialog.Builder(SubmissionActivity.this);
                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.custom_view, viewGroup, false);
                builder.setView(dialogView);
                alertDialog = builder.create();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.show();
            }
        });

    }

    public void returnHome(View view) {
        // When clicking the arrow button
        Intent homeIntent = new Intent(SubmissionActivity.this, MainActivity.class);
        startActivity(homeIntent);
    }

    public void OkButtonClicked(View view) {
        //When clicking the yes button
        SendProjctLink();
        alertDialog.dismiss();
    }

    public void CancelButtonClicked(View view) {
        //When clicking the cancel button
        Toast.makeText(getBaseContext(), "The submission of the project canceled", Toast.LENGTH_LONG).show();
        alertDialog.dismiss();
    }

    private void SendProjctLink() {

        // retrieve tha data from the editTexts
        String mName, mLastName, mEmail, mLink;
        mName = mNameEditText.getText().toString();
        mLastName = mLastNameEditText.getText().toString();
        mEmail = mEmailEditText.getText().toString();
        mLink = mProjectLinkEditText.getText().toString();

        // make sure that all field is written
        if (mName.isEmpty() || mLastName.isEmpty() || mEmail.isEmpty() || mLink.isEmpty()){
            Toast.makeText(getBaseContext(), "You have to complete all the field!", Toast.LENGTH_LONG).show();
            return;
        }
        LearnerListService taskService = ServiceBuilder.buildeService(LearnerListService.class);
        Call<Void> call = taskService.sendProject(BASE_URL, mName, mLastName, mEmail, mLink);
        // send the data
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> request, Response<Void> response) {
                //Show the succuseful dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(SubmissionActivity.this);
                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.success_custom_view, viewGroup, false);
                builder.setView(dialogView);
                AlertDialog succussalertDialog = builder.create();
                succussalertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                succussalertDialog.show();
            }

            @Override
            public void onFailure(Call<Void> request, Throwable t) {
                //Show the failed dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(SubmissionActivity.this);
                ViewGroup viewGroup = findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.failed_custom_view, viewGroup, false);
                builder.setView(dialogView);
                AlertDialog failedalertDialog = builder.create();
                failedalertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                failedalertDialog.show();
            }
        });
    }
}