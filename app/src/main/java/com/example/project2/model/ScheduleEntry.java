package com.example.project2.model;

public class ScheduleEntry {

    private String className;
    private String classType;
    private String professor;
    private String groups;
    private String day;
    private String time;
    private String classroom;

    public ScheduleEntry(String _className, String _classType, String _professor, String _groups, String _day, String _time, String _classroom) {
        className = _className;
        classType = _classType;
        professor = _professor;
        groups = _groups;
        day = _day;
        time = _time;
        classroom = _classroom;
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
