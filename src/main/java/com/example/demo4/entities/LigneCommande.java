package com.example.demo4.entities;

public class LigneCommande {
    private Long id;
    private Integer quantite;
    private Commande commande;
    private Produit produit;

    public LigneCommande(){};

    public LigneCommande(Long id, int quantite, Commande commande, Produit produit) {
        this.id = id;
        this.quantite = quantite;
        this.commande = commande;
        this.produit = produit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    @Override
    public String toString() {
        return "LigneCommande{" +
                "id=" + id +
                ", quantite=" + quantite +
                ", commande=" + commande +
                ", produit=" + produit +
                '}';
    }
}
