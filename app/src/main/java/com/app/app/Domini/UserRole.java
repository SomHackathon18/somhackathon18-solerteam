package com.app.app.Domini;

public class UserRole {

    private String email;
    private int id_role;

    //1->admin
    //2->botiguer
    //3->via

    public UserRole(String email, int id_role) {
        this.email = email;
        this.id_role = id_role;
    }

    public UserRole() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId_role() {
        return id_role;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }
}
