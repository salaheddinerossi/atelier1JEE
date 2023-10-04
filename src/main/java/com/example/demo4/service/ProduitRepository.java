package com.example.demo4.service;

import com.example.demo4.dao.ProduitDao;
import com.example.demo4.entities.Client;
import com.example.demo4.entities.Produit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProduitRepository extends ProduitDao {
    public ProduitRepository() throws SQLException, ClassNotFoundException {
        super();
    }

    public Produit saveProduit(Produit produit) throws SQLException {
        String request = "insert into produit (nom, prix) values (?, ?)";

        this.preparedStatement = this.myConnection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);

        this.preparedStatement.setString(1, produit.getNom());
        this.preparedStatement.setFloat(2, produit.getPrix());

        int affectedRows = this.preparedStatement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating produit failed, no rows affected.");
        }

        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                produit.setId(generatedKeys.getLong(1)); // Set the auto-generated id to Produit object
            } else {
                throw new SQLException("Creating produit failed, no ID obtained.");
            }
        }

        return produit;
    }
    // Retrieve a single product by ID.
    @Override
    public Produit getOneProduit(Long id) throws SQLException {
        String request = "select * from produit where id = ?";
        this.preparedStatement = this.myConnection.prepareStatement(request);
        this.preparedStatement.setLong(1, id);

        ResultSet resultSet = this.preparedStatement.executeQuery();
        if (resultSet.next()) {
            String nom = resultSet.getString("nom");
            float prix = resultSet.getFloat("prix");
            return new Produit(id, nom, prix);
        }
        return null;
    }

    // Delete a product by object.
    @Override
    public void deleteProduit(Produit produit) throws SQLException {
        String request = "delete from produit where id = ?";
        this.preparedStatement = this.myConnection.prepareStatement(request);
        this.preparedStatement.setLong(1, produit.getId());
        this.preparedStatement.executeUpdate();
    }

    // Update a product by object.
    @Override
    public void updateProduit(Produit produit) throws SQLException {
        String request = "update produit set nom = ?, prix = ? where id = ?";
        this.preparedStatement = this.myConnection.prepareStatement(request);
        this.preparedStatement.setString(1, produit.getNom());
        this.preparedStatement.setFloat(2, produit.getPrix());
        this.preparedStatement.setLong(3, produit.getId());
        this.preparedStatement.executeUpdate();
    }

    // Retrieve all products.
    @Override
    public List<Produit> getAllProduits() throws SQLException {
        List<Produit> produits = new ArrayList<>();
        String request = "select * from produit";
        this.preparedStatement = this.myConnection.prepareStatement(request);
        ResultSet resultSet = this.preparedStatement.executeQuery();
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            String nom = resultSet.getString("nom");
            float prix = resultSet.getFloat("prix");
            produits.add(new Produit(id, nom, prix));
        }
        return produits;
    }
}
