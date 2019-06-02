package com.example.project2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.project2.R;
import com.example.project2.adapter.SimplePagerAdapter;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private boolean mIsResultHandled = true;
    private static final int GOOGLE_SIGN_IN_REQUEST_CODE = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragments();
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
