package com.example.demo4.dao;

import com.example.demo4.entities.Client;

import java.sql.SQLException;
import java.util.List;

public abstract class ClientDao extends BaseDao{

    public ClientDao() throws SQLException, ClassNotFoundException {
        super();
    }


    public abstract Client saveClient(Client client) throws SQLException;

    public abstract void updateClient(Client client) throws SQLException;


    public abstract void deleteClient(Client client) throws SQLException;


    public abstract Client getOneClient(Long id ) throws SQLException;

    public abstract List<Client> getAllClients() throws SQLException;


}
