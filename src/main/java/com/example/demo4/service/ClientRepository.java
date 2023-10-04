package com.example.demo4.service;

import com.example.demo4.dao.ClientDao;
import com.example.demo4.entities.Client;


import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository extends ClientDao {

    public ClientRepository() throws SQLException, ClassNotFoundException {
        super();
    };
    @Override

    public Client saveClient(Client client) throws SQLException {
        String request = "insert into client (nom , adresse) values (? , ?)";

        this.preparedStatement = this.myConnection.prepareStatement(request, Statement.RETURN_GENERATED_KEYS);

        this.preparedStatement.setString(1, client.getNom());
        this.preparedStatement.setString(2, client.getAdresse());

        this.preparedStatement.executeUpdate();

        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                client.setId(generatedKeys.getLong(1)); // Set the auto-generated id to client object
            } else {
                throw new SQLException("Creating client failed, no ID obtained.");
            }
        }

        return client; // Return the client object with the updated ID
    }

    @Override
    public List<Client> getAllClients() throws SQLException {
        String request = "SELECT * FROM client";
        List<Client> clients = new ArrayList<>();
        try (PreparedStatement preparedStatement = this.myConnection.prepareStatement(request);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getLong("id"));
                client.setNom(resultSet.getString("nom"));
                client.setAdresse(resultSet.getString("adresse"));
                clients.add(client);
            }
        } catch (SQLException e) {
            // Log and/or re-throw exception
            throw e;
        }
        return clients;
    }

    @Override
    public Client getOneClient(Long id) throws SQLException {
        String request = "SELECT * FROM client WHERE id = ?";
        try (PreparedStatement preparedStatement = this.myConnection.prepareStatement(request)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Client client = new Client();
                    client.setId(resultSet.getLong("id"));
                    client.setNom(resultSet.getString("nom"));
                    client.setAdresse(resultSet.getString("adresse"));
                    return client;
                }
            }
        } catch (SQLException e) {
            // Log and/or re-throw exception
            throw e;
        }
        return null;
    }

    @Override
    public void deleteClient(Client client) throws SQLException {
        String request = "DELETE FROM client WHERE id = ?";
        try (PreparedStatement preparedStatement = this.myConnection.prepareStatement(request)) {
            preparedStatement.setLong(1, client.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Log and/or re-throw exception
            throw e;
        }
    }

    @Override
    public void updateClient(Client client) throws SQLException {
        String request = "UPDATE client SET nom = ?, adresse = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = this.myConnection.prepareStatement(request)) {
            preparedStatement.setString(1, client.getNom());
            preparedStatement.setString(2, client.getAdresse());
            preparedStatement.setLong(3, client.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Log and/or re-throw exception
            throw e;
        }
    }


}
