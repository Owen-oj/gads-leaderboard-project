package com.example.leaderboard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.leaderboard.models.LearningLeader;
import com.example.leaderboard.network.GadsApiService;
import com.example.leaderboard.network.RetrofitInstance;
import com.example.leaderboard.ui.main.LearningRecyclerViewAdapter;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormActivity extends AppCompatActivity {

    private EditText first_name;
    private EditText last_name;
    private EditText email;
    private EditText github_link;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.form_toolbar);
        getSupportActionBar();
        Objects.requireNonNull(getSupportActionBar()).hide();
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
        setContentView(R.layout.activity_form);
    }


    public void confirmSubmission(View view) {
        first_name = findViewById(R.id.first_name);
        last_name = findViewById(R.id.last_name);
        email = findViewById(R.id.email);
        github_link = findViewById(R.id.link);

        Boolean validRequest = true;

        if (TextUtils.isEmpty(first_name.getText())) {
            first_name.setError("First Name is Required");
            validRequest = false;
        }
        if (TextUtils.isEmpty(last_name.getText())) {
            last_name.setError("Last Name is Required");
            validRequest = false;
        }
        if (!isValidEmail(email.getText().toString())) {
            email.setError("Valid Email is Required");
            validRequest = false;
        }
        if (TextUtils.isEmpty(github_link.getText())) {
            github_link.setError("Github Link is Required");
            validRequest = false;
        }

        if (validRequest) {
            AlertDialog.Builder alertDialogBuilder= new AlertDialog.Builder(this);
            alertDialogBuilder.setView(R.layout.custom_alert_dialogue);
             alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        } else {

        }


    }

    public void submitProject(View view) {
        alertDialog.hide();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.show();
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        GadsApiService gadsApiService = RetrofitInstance.getRetrofitInstance("https://docs.google.com/forms/d/e/").create(GadsApiService.class);

        Call<Void> call = gadsApiService.submitProject(email.getText().toString(), first_name.getText().toString(), last_name.getText().toString(), github_link.getText().toString());

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                progressDialog.cancel();
                alertDialogBuilder.setView(R.layout.success_alert);
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.setCanceledOnTouchOutside(true);
                alertDialog.show();

                Log.d("TAG", "IsSuccessful? = " + response.isSuccessful());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                alertDialogBuilder.setView(R.layout.error_alert);
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.setCanceledOnTouchOutside(true);
                alertDialog.show();
                Log.e("ERROR", "Error submitting");

            }
        });

    }


    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

}