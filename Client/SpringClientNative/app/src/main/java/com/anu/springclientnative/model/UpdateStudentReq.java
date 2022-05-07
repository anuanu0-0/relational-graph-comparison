package com.anu.springclientnative.model;

public class UpdateStudentReq {

    private Long id;
    private String name;
    private String country;
    private int birthYear;

    public UpdateStudentReq(Long id, String name, String country, int birthYear) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.birthYear = birthYear;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public int getBirthYear() {
        return birthYear;
    }
}
