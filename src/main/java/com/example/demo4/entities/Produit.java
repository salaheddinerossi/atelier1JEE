package com.example.demo4.entities;

public class Produit {


    private Long id;
    private String nom;
    private Float prix;

    public Produit(){

    };

    public Produit(Long id, String nom ,Float prix){
        this.id=id;
        this.nom=nom;
        this.prix=prix;

    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Float getPrix() {
        return prix;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                '}';
    }
}
