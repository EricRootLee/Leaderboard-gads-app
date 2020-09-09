package com.example.leaderboard.UIs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.leaderboard.R;
import com.example.leaderboard.retrofitApi.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubmissionActivity extends AppCompatActivity {

    private TextView mProjectSubmission;
    private EditText mFirstName;
    private EditText mLastName;
    private EditText mEmailAddress;
    private EditText mGitHubLink;
    private Button mSubmitActivity;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission);


        mToolbar = findViewById(R.id.toolbar);
        mProjectSubmission = findViewById(R.id.text_view_project_submission);
        mFirstName = findViewById(R.id.eTFirstName);
        mLastName = findViewById(R.id.eTLastName);
        mEmailAddress = findViewById(R.id.eT_email_address);
        mGitHubLink = findViewById(R.id.eT_GithubLink);
        mSubmitActivity = findViewById(R.id.button_submit_activity);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mSubmitActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitProject();
            }

        });
    }
    private void submitProject() {

        if (inputIsValid()){
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
            final AlertDialog alertDialog = alertBuilder.create();
            alertDialog.show();
            alertDialog.getWindow().setLayout(900,1000);
            final LayoutInflater inflater = this.getLayoutInflater();
            final View alertView = inflater.inflate(R.layout.confirm_submission,null);
            alertDialog.getWindow().setContentView(alertView);

            ImageView cancel = alertView.findViewById(R.id.image_view_cancel);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();
                }
            });

            Button yesButton = alertView.findViewById(R.id.button_yes);
            yesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();
                    submitProjectToServer();
                }

                private void submitProjectToServer() {
                    String firstName = mFirstName.getText().toString().trim();
                    String lastName = mLastName.getText().toString().trim();
                    String email = mEmailAddress.getText().toString().trim();
                    String LinkToProject = mGitHubLink.getText().toString().trim();

                    ApiClient.getGoogleDocsClient().submitProject(email,firstName,lastName,LinkToProject).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()){
                                Log.d("TAG","submitted successfully" + response.body());
                                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(SubmissionActivity.this);
                                AlertDialog alertDialog1 = alertBuilder.create();
                                alertDialog1.show();
                                alertDialog1.getWindow().setLayout(900,1000);
                                LayoutInflater layoutInflater = SubmissionActivity.this.getLayoutInflater();
                                View view = layoutInflater.inflate(R.layout.submission_successful,null);
                                alertDialog1.getWindow().setContentView(view);
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Log.d("TAG","submission failure" + t.getMessage());
                            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(SubmissionActivity.this);
                            AlertDialog alertDialog2 = alertBuilder.create();
                            alertDialog2.show();
                            alertDialog2.getWindow().setLayout(900,1000);
                            LayoutInflater layoutInflater = SubmissionActivity.this.getLayoutInflater();
                            View view = layoutInflater.inflate(R.layout.submission_not_successfull,null);
                            alertDialog2.getWindow().setContentView(view);
                        }
                    });
                }
            });

        }
    }

    private boolean inputIsValid() {
        if (mFirstName.getText().toString().trim().equals("")){
            mFirstName.setError("First name is required");
            return false;
        }
        if (mLastName.getText().toString().trim().equals("")) {
            mLastName.setError("Last name is required");
            return false;
        }
        if (mEmailAddress.getText().toString().trim().equals("")) {
            mEmailAddress.setError("Email address is required");
            return false;
        }
        if (mGitHubLink.getText().toString().trim().equals("")) {
            mGitHubLink.setError("GitHub link is required");
            return false;
        }
        return true;
    }


}
