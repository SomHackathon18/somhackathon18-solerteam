package com.app.app.Domini;

import com.app.app.Domini.DTO.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.Date;

public class User {

    private String name;
    private String surname;
    private String address;
    private String telf;
    private String email;
    private String password;
    private String gender;
    private LocalDateTime birthday;

    public User() {
    }

    private User(userBuilder builder) {

        this.name = builder.name;
        this.surname = builder.surname;
        this.address = builder.address;
        this.telf = builder.telefon;
        this.email = builder.email;
        this.password = builder.password;
        this.gender = builder.gender;
        this.birthday = builder.birthday;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelf() {
        return telf;
    }

    public void setTelf(String telf) {
        this.telf = telf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", address='" + address + '\'' +
                ", telf='" + telf + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                '}';
    }

    public UserDTO converetToDTO() {
        return new UserDTO(name, surname, email, telf, gender, address);
    }

    public static class userBuilder {

        private String name;
        private String surname;
        private String address;
        private String telefon;
        private String email;
        private String password;
        private String gender;
        private LocalDateTime birthday;

        public userBuilder() {
        }

        public userBuilder name(String nom) {
            this.name = nom;
            return this;
        }

        public userBuilder surname(String cognom1) {
            this.surname = cognom1;
            return this;
        }

        public userBuilder address(String adreca) {
            this.address = adreca;
            return this;
        }


        public userBuilder telefon(String telefon) {
            this.telefon = telefon;
            return this;
        }

        public userBuilder email(String email) {
            this.email = email;
            return this;
        }

        public userBuilder password(String password) {
            this.password = password;
            return this;
        }

        public userBuilder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public userBuilder birthday(LocalDateTime birthday) {
            this.birthday = birthday;
            return this;
        }


        public User build() {
            return new User(this);
        }


    }
}
