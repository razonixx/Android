package com.example.feb14;

public class Student {

    //java beans
    //class that has only information

    private String name;
    private float grade;
    private long studentID;

    public Student(String name, float grade, long studentID){
        this.name = name;
        this.grade = grade;
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public float getGrade(){
        return grade;
    }

    public long getStudentID(){return studentID;}
}
