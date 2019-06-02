package com.example.project2.repository.web.model;

import com.google.gson.annotations.SerializedName;

public class ScheduleApiModel {

    @SerializedName("predmet")
    private String className;

    @SerializedName("tip")
    private String classType;

    @SerializedName("nastavnik")
    private String professor;

    @SerializedName("grupe")
    private String groups;

    @SerializedName("dan")
    private String day;

    @SerializedName("termin")
    private String time;

    @SerializedName("ucionica")
    private String classroom;


    public String getClassName() {
        return className;
    }

    public String getClassType() {
        return classType;
    }

    public String getProfessor() {
        return professor;
    }

    public String getGroups() {
        return groups;
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    public String getClassroom() {
        return classroom;
    }

}
