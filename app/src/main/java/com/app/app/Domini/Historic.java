package com.app.app.Domini;

public class Historic {

    private String nomCarrer;
    private int quantitat;

    public Historic(String nomCarrer, int quantitat) {
        this.nomCarrer = nomCarrer;
        this.quantitat = quantitat;
    }

    public Historic() {

    }

    public String getNomCarrer() {
        return nomCarrer;
    }

    public void setNomCarrer(String nomCarrer) {
        this.nomCarrer = nomCarrer;
    }

    public int getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(int quantitat) {
        this.quantitat = quantitat;
    }
}
