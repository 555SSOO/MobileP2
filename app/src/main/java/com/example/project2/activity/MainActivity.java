package com.example.project2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.project2.R;
import com.example.project2.adapter.SimplePagerAdapter;
import com.example.project2.viewmodel.MainViewModel;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.material.tabs.TabLayout;

import java.util.Arrays;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    MainViewModel mainViewModel;
    private static final String TAG = "MainActivity";
    private boolean mIsResultHandled = true;
    private static final int GOOGLE_SIGN_IN_REQUEST_CODE = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViewModel();
        initFragments();
    }

    private void initViewModel(){

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mainViewModel.getAuthLiveData().observe(this,
                firebaseUser -> {
                    Log.e(TAG, "MainActivity onChanged: " + firebaseUser);
                    if (firebaseUser == null) {
                        // User is not logged in, start Firebase Auth UI log in flow
                        if (mIsResultHandled) {
                            mIsResultHandled = false;
                            Intent intent = AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setAlwaysShowSignInMethodScreen(true)
                                    .setLogo(R.drawable.common_google_signin_btn_icon_dark)
                                    .setAvailableProviders(Arrays.asList(
                                            new AuthUI.IdpConfig.GoogleBuilder().build()
                                    ))
                                    .build();
                            startActivityForResult(intent, GOOGLE_SIGN_IN_REQUEST_CODE);
                        }
                    } else {
                        // User is logged in, welcome him!
                        Toast.makeText(MainActivity.this, "Welcome " + firebaseUser.getDisplayName(), Toast.LENGTH_LONG).show();
                        // Register new observer so that activity can get employee data
//                            mMainViewModel.getEmployeeLiveData().observe(MainActivity.this, mEmployeeObserver);
                    }
                });


    }

    private void initFragments() {
        ViewPager viewPager = findViewById(R.id.vp_main);
        SimplePagerAdapter adapter = new SimplePagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GOOGLE_SIGN_IN_REQUEST_CODE) {
            if (resultCode == RESULT_CANCELED) {
                finish();
            } else  if (resultCode == RESULT_OK){
                IdpResponse response = IdpResponse.fromResultIntent(data);
                if (response != null && response.isNewUser()) {
                    Log.e(TAG, "onActivityResult: We have a new user, should we store him?");
                }
                mIsResultHandled = true;
            }
        }
    }

}
