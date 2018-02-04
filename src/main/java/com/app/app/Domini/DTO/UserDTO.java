package com.app.app.Domini.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDTO {

    private String name;
    private String surname;
    private String email;
    private String telefon;
    private String gender;
    private String address;

    @JsonIgnore
    private String token;

    public UserDTO() {
    }

    public UserDTO(String name, String surname, String email,
                   String telefon, String gender,
                   String address) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.telefon = telefon;
        this.gender = gender;
        this.address = address;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
