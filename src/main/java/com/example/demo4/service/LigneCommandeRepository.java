package com.example.demo4.service;

import com.example.demo4.dao.LigneCommandeDAO;
import com.example.demo4.entities.Commande;
import com.example.demo4.entities.LigneCommande;
import com.example.demo4.entities.Produit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LigneCommandeRepository extends LigneCommandeDAO {
    public LigneCommandeRepository() throws SQLException, ClassNotFoundException {
        super();
    };

    @Override
    public LigneCommande saveLigneCommande(LigneCommande ligneCommande) throws SQLException {
        String request = "insert into linedecommande (quantite, idCommande, idProduit) values (?, ?, ?)";

        this.preparedStatement = this.myConnection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);

        this.preparedStatement.setInt(1, ligneCommande.getQuantite());
        this.preparedStatement.setLong(2, ligneCommande.getCommande().getId());
        this.preparedStatement.setLong(3, ligneCommande.getProduit().getId());

        this.preparedStatement.executeUpdate();

        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                ligneCommande.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("Creating ligneCommande failed, no ID obtained.");
            }
        }

        return ligneCommande;
    }

    public Float priceByCommande(Commande commande) throws SQLException {
        Float totalPrice = 0.0F;

        String query = "SELECT lc.*, p.prix " +
                "FROM linedecommande lc " +
                "JOIN produit p ON lc.idProduit = p.id " +
                "WHERE lc.idCommande = ?";

        try (PreparedStatement stmt = this.myConnection.prepareStatement(query)) {
            stmt.setLong(1, commande.getId());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    totalPrice += rs.getFloat("prix") * rs.getInt("quantite");
                }
            }
        }

        return totalPrice;
    }

    public List<LigneCommande> findLignesCommandeByCommande(Commande commande) throws SQLException {
        List<LigneCommande> lignesCommande = new ArrayList<>();

        String query = "SELECT * FROM linedecommande WHERE idCommande = ?";

        try (PreparedStatement stmt = this.myConnection.prepareStatement(query)) {
            stmt.setLong(1, commande.getId());

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    LigneCommande ligne = new LigneCommande();
                    ligne.setId(rs.getLong("id"));
                    ligne.setQuantite(rs.getInt("quantite"));


                    ProduitRepository produitRepository = new ProduitRepository();
                    Produit p1 = produitRepository.getOneProduit(rs.getLong("idProduit"));
                    ligne.setProduit(p1);

                    CommandeRepository commandeRepository = new CommandeRepository();
                    Commande c1 = commandeRepository.getOneCommande(rs.getLong("idCommande"));
                    ligne.setCommande(c1);




                    lignesCommande.add(ligne);
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        return lignesCommande;
    }


}
