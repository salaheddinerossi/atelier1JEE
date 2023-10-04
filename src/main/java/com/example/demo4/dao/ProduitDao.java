package com.example.demo4.dao;

import com.example.demo4.entities.Client;
import com.example.demo4.entities.Produit;

import java.sql.SQLException;
import java.util.List;

public abstract class ProduitDao extends BaseDao {
    public ProduitDao()throws SQLException, ClassNotFoundException{
        super();
    }



    public abstract Produit saveProduit(Produit client) throws SQLException;

    public abstract void updateProduit(Produit client) throws SQLException;


    public abstract void deleteProduit(Produit client) throws SQLException;


    public abstract Produit getOneProduit(Long id ) throws SQLException;

    public abstract List<Produit> getAllProduits() throws SQLException;


}
