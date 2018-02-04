package com.appenjoyer.developer.walkdetector;

/**
 * Created by Design on 03/02/2018.
 */

public class UserPOJO {
    private String name;
    private String surname;
    private String address;
    private String telf;
    private String email;
    private String password;
    private String gender;
    private String birthday;

    public UserPOJO(String name, String surname, String address, String telf, String email, String password, String gender, String birthday) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.telf = telf;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.birthday = birthday;
    }

    public UserPOJO(String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getTelf() {
        return telf;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;

    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTelf(String telf) {
        this.telf = telf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
