package com.example.demo4.service;

import com.example.demo4.dao.CommandeDAO;
import com.example.demo4.entities.Client;
import com.example.demo4.entities.Commande;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CommandeRepository extends CommandeDAO {

    public CommandeRepository() throws SQLException, ClassNotFoundException {
        super();
    }


    @Override
    public Commande saveCommande(Commande commande) throws SQLException {
        String request = "insert into commande (idClient) values (?)";

        this.preparedStatement = this.myConnection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);

        // MOR mapping object relation
        this.preparedStatement.setLong(1, commande.getClient().getId());

        int affectedRows = this.preparedStatement.executeUpdate();

        if (affectedRows == 0) {
            throw new SQLException("Creating commande failed, no rows affected.");
        }

        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                commande.setId(generatedKeys.getLong(1)); // Set the auto-generated id to Commande object
            } else {
                throw new SQLException("Creating commande failed, no ID obtained.");
            }
        }

        return commande; // Return the Commande object with the updated ID
    }

    @Override
    public void deleteCommande(Commande commande) throws SQLException {
        String request = "delete from commande where id = ?";
        try(PreparedStatement preparedStatement = this.myConnection.prepareStatement(request)) {
            preparedStatement.setLong(1, commande.getId());
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("Deleting commande failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
    @Override
    public List<Commande> getAllCommandes() throws SQLException {
        List<Commande> commandes = new ArrayList<>();
        String request = "select * from commande";

        try(PreparedStatement preparedStatement = this.myConnection.prepareStatement(request);
            ResultSet resultSet = preparedStatement.executeQuery()) {

            ClientRepository clientRepository = new ClientRepository();

            while(resultSet.next()) {
                Long id = resultSet.getLong("id");
                Long idClient = resultSet.getLong("idClient");
                Client client = clientRepository.getOneClient(idClient);

                if(client != null) { // check if a client is found for given idClient
                    Commande commande = new Commande(id, client);
                    commandes.add(commande);
                } else {
                    // log or handle the case where no client is found for a given idClient
                }
            }
        } catch(SQLException e) {
            e.printStackTrace(); // or log using a logging framework
            throw e; // or handle the exception as appropriate
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return commandes;
    }

    public Commande getOneCommande(Long id) throws SQLException {
        Commande commande = null;
        String request = "select * from commande where id = ?";

        try(PreparedStatement preparedStatement = this.myConnection.prepareStatement(request)) {
            preparedStatement.setLong(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                ClientRepository clientRepository = new ClientRepository(); // Or however you obtain a ClientRepository instance

                if(resultSet.next()) {
                    Long commandeId = resultSet.getLong("id");
                    Long idClient = resultSet.getLong("idClient");
                    Client client = clientRepository.getOneClient(idClient); // Assuming you have a method to get one client by id

                    if(client != null) { // check if a client is found for the given idClient
                        commande = new Commande(commandeId, client);
                    } else {
                        // log or handle the case where no client is found for a given idClient
                    }
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch(SQLException e) {
            e.printStackTrace(); // or log using a logging framework
            throw e; // or handle the exception as appropriate
        }

        return commande;
    }



}
