package com.example.project2.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project2.R;
import com.example.project2.model.UserResponse;
import com.example.project2.viewmodel.LoginViewModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private String indexPattern = "[Rr][MmNn]-[0-9][0-9]-[0-9][0-9]";

    private LoginViewModel mLogInViewModel;
    private EditText mUserNameEt;
    private EditText mIndexIdEt;
    private Button mButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        initUi();
        initViewModel();
    }

    private void initUi() {
        mUserNameEt = findViewById(R.id.et_log_in_username);
        mIndexIdEt = findViewById(R.id.et_log_in_index_id);
        mButton = findViewById(R.id.btn_log_in);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mUserNameEt.getText().toString();
                String indexId = mIndexIdEt.getText().toString();

                Pattern pattern = Pattern.compile(indexPattern);
                Matcher matcher = pattern.matcher(indexId);
                boolean matches = matcher.matches();

                if (name.trim().isEmpty() || !matches) {
                    Toast.makeText(LoginActivity.this, "Input not valid", Toast.LENGTH_LONG).show();
                } else {
                    mButton.setEnabled(false);
                    mLogInViewModel.logInUser(indexId, name);
                }


            }
        });
    }

    private void initViewModel() {
        mLogInViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        mLogInViewModel.getUserStoreLiveData().observe(this, new Observer<UserResponse>() {
            @Override
            public void onChanged(UserResponse userResponse) {
                if (userResponse.isSuccessful()) {
                    Log.e(TAG, "onChanged: user stored in FireBase db and shared pref" + userResponse.getUser().toString());
                    // We can either send user info through intent, or observe UserStoreLiveData in MainActivity
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    mButton.setEnabled(true);
                    Toast.makeText(LoginActivity.this, "Log in failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
