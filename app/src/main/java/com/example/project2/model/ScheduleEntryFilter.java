package com.example.project2.model;

public class ScheduleEntryFilter {

    private String className;
    private String professor;
    private String day;
    private String group;
    private String classroom;

    public ScheduleEntryFilter(String _className, String _professor, String _day, String _group, String _classroom) {
        className = _className;
        professor = _professor;
        day = _day;
        group = _group;
        classroom = _classroom;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

}
