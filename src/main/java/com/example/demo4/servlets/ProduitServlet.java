package com.example.demo4.servlets;

import com.example.demo4.entities.Produit;
import com.example.demo4.service.ProduitRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/produit")
public class ProduitServlet extends HttpServlet {
    private ProduitRepository produitRepository = new ProduitRepository();

    public ProduitServlet() throws SQLException, ClassNotFoundException {
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String deleteId = req.getParameter("deleteId");

        if (deleteId != null) {
            try {
                Produit produitToDelete = produitRepository.getOneProduit(Long.parseLong(deleteId));
                if (produitToDelete != null) {
                    produitRepository.deleteProduit(produitToDelete);
                } else {
                }
            } catch (SQLException e) {
            }
        }

        try {
            List<Produit> produits = produitRepository.getAllProduits();
            req.setAttribute("produits", produits);
        } catch (SQLException e) {
            throw new ServletException(e);
        }

        req.getRequestDispatcher("/produit.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("update".equals(action)) {
            try {
                Long produitId = Long.parseLong(req.getParameter("produitId"));
                String nom = req.getParameter("nom");
                Float prix = Float.parseFloat(req.getParameter("prix"));

                Produit produit = produitRepository.getOneProduit(produitId);
                produit.setNom(nom);
                produit.setPrix(prix);

                produitRepository.updateProduit(produit);
                resp.sendRedirect("produit");
                return;
            } catch (SQLException | NumberFormatException e) {
                throw new ServletException(e);
            }
        }

        String nom = req.getParameter("nom");
        Float prix = Float.parseFloat(req.getParameter("prix"));

        Produit produit = new Produit();
        produit.setNom(nom);
        produit.setPrix(prix);

        try {
            produitRepository.saveProduit(produit);
            resp.sendRedirect("produit");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
