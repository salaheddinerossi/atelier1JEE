package com.example.demo4.dao;

import com.example.demo4.dao.BaseDao;
import com.example.demo4.entities.Client;
import com.example.demo4.entities.Commande;

import java.sql.SQLException;
import java.util.List;

public abstract class CommandeDAO extends BaseDao {
    public CommandeDAO() throws SQLException, ClassNotFoundException {
        super();
    }
    public abstract Commande saveCommande(Commande commande)throws SQLException;



    public abstract void deleteCommande(Commande commande) throws SQLException;


    public abstract Commande getOneCommande(Long id ) throws SQLException;

    public abstract List<Commande> getAllCommandes() throws SQLException;


}

