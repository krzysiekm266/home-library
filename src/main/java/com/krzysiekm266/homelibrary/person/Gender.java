package com.krzysiekm266.homelibrary.person;

public enum Gender {
    MALE("MALE"),
    FAMALE("FEMALE");
    private String gender;
    private Gender(String gender) {
        this.gender = gender;
    }
    public String getGender() {
        return gender;
    }
}
