package com.example.demo4.servlets;

import com.example.demo4.entities.Client;
import com.example.demo4.entities.Commande;
import com.example.demo4.entities.LigneCommande;
import com.example.demo4.entities.Produit;
import com.example.demo4.service.ClientRepository;
import com.example.demo4.service.CommandeRepository;
import com.example.demo4.service.LigneCommandeRepository;
import com.example.demo4.service.ProduitRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/commande")
public class CommandeServlet extends HttpServlet {
    CommandeRepository commandeRepository = new CommandeRepository();
    ProduitRepository produitRepository = new ProduitRepository();
    LigneCommandeRepository ligneCommandeRepository=new LigneCommandeRepository();
    ClientRepository clientRepository = new ClientRepository();
    public CommandeServlet() throws SQLException, ClassNotFoundException {
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp)throws IOException, ServletException {
        try{
            List<Commande> commandes = commandeRepository.getAllCommandes();
            List<Produit> produits = produitRepository.getAllProduits();
            List<Client> clients = clientRepository.getAllClients();
            req.setAttribute("produits",produits);
            req.setAttribute("commandes",commandes);
            req.setAttribute("clients",clients);
            System.out.println(commandes);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        req.getRequestDispatcher("/commande.jsp").forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null && action.equals("delete")) {
            int id = Integer.parseInt(req.getParameter("id"));
            try {
                Commande toDeleteCommande = commandeRepository.getOneCommande(Long.valueOf(id));
                commandeRepository.deleteCommande(toDeleteCommande);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            // Redirect back to the list after deleting
            resp.sendRedirect(req.getContextPath() + "/commande");
        }
        if ("create".equals(action)) {
            try {
                handleCreateCommande(req, resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


    }

    private void handleCreateCommande(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        Commande newCommande = new Commande();


        String clientIdStr = req.getParameter("selected_client");
        if (clientIdStr != null) {
            int clientId = Integer.parseInt(clientIdStr);
            Client generatedClient = clientRepository.getOneClient(Long.valueOf(clientId));
            newCommande.setClient(generatedClient);
        }

        Commande generatedCommande;
        try {
            generatedCommande = commandeRepository.saveCommande(newCommande);

            // Handle LigneCommande for each product:
            for (String key : req.getParameterMap().keySet()) {
                if (key.startsWith("product_")) {
                    int productId = Integer.parseInt(key.substring(8));
                    Produit generatedProduit =produitRepository.getOneProduit(Long.valueOf(productId));
                    int quantity = Integer.parseInt(req.getParameter(key));

                    if (quantity > 0) {
                        LigneCommande ligneCommande = new LigneCommande();
                        ligneCommande.setProduit(generatedProduit);
                        ligneCommande.setQuantite(quantity);
                        ligneCommande.setCommande(generatedCommande);

                        ligneCommandeRepository.saveLigneCommande(ligneCommande); // Assuming this method is available
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect("commande");

    }


}
