package com.example.project2.model;

public class ScheduleEntryFilter {

    private String mTitle;
    private String mReleaseDate;
    private int mScore;

    public ScheduleEntryFilter(String title, String releaseDate, int score) {
        mTitle = title;
        mReleaseDate = releaseDate;
        mScore = score;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public int getScore() {
        return mScore;
    }

    public void setScore(int score) {
        mScore = score;
    }
}
