package com.example.demo4.dao;


import com.example.demo4.entities.Commande;
import com.example.demo4.entities.LigneCommande;

import java.sql.SQLException;
import java.util.List;

public abstract class LigneCommandeDAO extends BaseDao{
    public LigneCommandeDAO() throws SQLException, ClassNotFoundException {
        super();
    }

    public abstract LigneCommande saveLigneCommande(LigneCommande lingcommande)throws SQLException;

    public abstract Float priceByCommande(Commande commande) throws  SQLException;

    public abstract List<LigneCommande> findLignesCommandeByCommande(Commande commande) throws SQLException;

}
