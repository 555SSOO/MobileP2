package com.example.project2.adapter;

import android.content.Context;

import com.example.project2.R;
import com.example.project2.fragment.ChatFragment;
import com.example.project2.fragment.ScheduleFragment;
import com.example.project2.fragment.WallFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.annotation.Nullable;


public class SimplePagerAdapter extends FragmentPagerAdapter {

    private static final int FRAGMENT_COUNT = 3;

    private List<String> mTitles;

    public SimplePagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        initTitles(context);

    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return ScheduleFragment.newInstance();
            case 1:
                return ChatFragment.newInstance();
            case 2:
                return WallFragment.newInstance();
        }

        return null;
    }

    @Override
    public int getCount() {
        return FRAGMENT_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

    private void initTitles(Context context) {
        mTitles = new ArrayList<>();
        mTitles.add(context.getString(R.string.fragment_title_1));
        mTitles.add(context.getString(R.string.fragment_title_2));
        mTitles.add(context.getString(R.string.fragment_title_3));
    }
}