package com.example.demo4.servlets;

import com.example.demo4.entities.Client;
import com.example.demo4.service.ClientRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.inject.Inject;
import java.io.*;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/client")
public class ClientServlet extends HttpServlet {
    private ClientRepository clientRepository =new ClientRepository();

    public ClientServlet() throws SQLException, ClassNotFoundException {
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String deleteId = req.getParameter("deleteId");

        if (deleteId != null) {
            try {
                Client clientToDelete = clientRepository.getOneClient(Long.parseLong(deleteId));
                if (clientToDelete != null) {
                    clientRepository.deleteClient(clientToDelete);
                } else {
                }
            } catch (SQLException e) {
            }
        }


        try {
            List<Client> clients = clientRepository.getAllClients();
            req.setAttribute("clients", clients);
        } catch (SQLException e) {
            try {
                throw new ServletException(e);
            } catch (ServletException ex) {
                throw new RuntimeException(ex);
            }
        }



        req.getRequestDispatcher("/client.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String action = req.getParameter("action");



        if ("update".equals(action)) {
            String name = req.getParameter("nom");
            String address = req.getParameter("adresse");
            String clientIds = req.getParameter("clientId");
            Long clientId=Long.parseLong(clientIds);

            // Retrieve the client, update fields, and persist changes
            Client client = null;
            try {
                client = clientRepository.getOneClient(clientId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            client.setNom(name);
            client.setAdresse(address);
            try {
                clientRepository.updateClient(client);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            // Redirect and exit the method
            resp.sendRedirect("client");
            return;
        }

        try{
            String nom = req.getParameter("nom");
            String adresse = req.getParameter("adresse");

            Client client = new Client();
            client.setNom(nom);
            client.setAdresse(adresse);

            resp.sendRedirect("client");
            clientRepository.saveClient(client);

        }catch (SQLException  e){
            throw new ServletException("Error saving client", e);
        }
    }



}
