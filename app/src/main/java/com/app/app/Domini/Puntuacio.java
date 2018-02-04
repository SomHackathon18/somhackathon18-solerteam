package com.app.app.Domini;

public class Puntuacio {

    private String userEmail;
    private int quantitat;
    private int id;

    public Puntuacio(String userEmail, int quantitat) {
        this.userEmail = userEmail;
        this.quantitat = quantitat;
    }

    public Puntuacio() {

    }

    public Puntuacio(int id_puntuacio, String email, int quantitat) {
        this.id = id_puntuacio;
        this.userEmail = email;
        this.quantitat = quantitat;
    }

    public Puntuacio(int id_puntuacio) {
        this.id = id_puntuacio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(int quantitat) {
        this.quantitat = quantitat;
    }

    @Override
    public String toString() {
        return "Puntuacio{" +
                "userEmail='" + userEmail + '\'' +
                ", quantitat=" + quantitat +
                ", id=" + id +
                '}';
    }
}
