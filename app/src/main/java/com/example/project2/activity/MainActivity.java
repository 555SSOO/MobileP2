package com.example.project2.activity;

import android.os.Bundle;

import com.example.project2.R;
import com.example.project2.adapter.SimplePagerAdapter;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        ViewPager viewPager = findViewById(R.id.vp_main);
        SimplePagerAdapter adapter = new SimplePagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
}
