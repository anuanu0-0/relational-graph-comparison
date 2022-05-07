package com.anu.springclientnative.model;

import java.util.List;


public class Student {

    private String name;
    private int birthYear;
    private String country;
    private List<Subject> subjectList;
    private Department department;

    public Student(String name, int birthYear, String country, List<Subject> subjectList, Department department) {
        this.name = name;
        this.birthYear = birthYear;
        this.country = country;
        this.subjectList = subjectList;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}


