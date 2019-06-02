package com.example.project2.repository.db.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "schedule_entries")
public class ScheduleEntryEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String id;

    @ColumnInfo(name = "predmet")
    private String className;

    @ColumnInfo(name = "tip")
    private String classType;

    @ColumnInfo(name = "nastavnik")
    private String professor;

    @ColumnInfo(name = "grupe")
    private String groups;

    @ColumnInfo(name = "dan")
    private String day;

    @ColumnInfo(name = "termin")
    private String time;

    @ColumnInfo(name = "ucionica")
    private String classroom;

    public ScheduleEntryEntity(@NonNull String id, String className, String classType, String professor, String groups, String day, String time, String classroom) {
        this.id = id;
        this.className = className;
        this.classType = classType;
        this.professor = professor;
        this.groups = groups;
        this.day = day;
        this.time = time;
        this.classroom = classroom;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getGroups() {
        return groups;
    }

    public void setGroups(String groups) {
        this.groups = groups;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

}
