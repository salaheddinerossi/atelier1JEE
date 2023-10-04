package com.example.demo4.entities;

import java.sql.Timestamp;

public class Commande {

    private Long id;
    private Timestamp date;
    private Client Client;

    public Commande(){

    };

    public Commande(Long id, Client client) {
        this.id = id;
        Client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public com.example.demo4.entities.Client getClient() {
        return Client;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public void setClient(com.example.demo4.entities.Client client) {
        Client = client;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id=" + id +
                ", date=" + date +
                ", Client=" + Client +
                '}';
    }
}
