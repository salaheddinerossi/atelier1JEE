<%@ page import="com.example.demo4.entities.Commande" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.demo4.service.LigneCommandeRepository" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.example.demo4.entities.LigneCommande" %>
<%@ page import="com.example.demo4.entities.Produit" %>
<%@ page import="com.example.demo4.entities.Client" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Commandes</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container mt-5">
    <h2>List of Commandes</h2>
    <ul class="list-group">
        <%
            List<Commande> malist = (List<Commande>) request.getAttribute("commandes");
            List<Produit> produits = (List<Produit>) request.getAttribute("produits");
            List<Client> clients = (List<Client>) request.getAttribute("clients");
            LigneCommandeRepository ligneCommandeRepository1 = new LigneCommandeRepository();
            for (Commande tmp : malist) {
        %>
        <li class="list-group-item">
            <%= tmp.getId() %>
            <strong>Client:</strong> <%= tmp.getClient().getNom() %><br>
            <strong>Les Produits:</strong>
            <% for (LigneCommande lc : ligneCommandeRepository1.findLignesCommandeByCommande(tmp)) { %>
            <%= lc.getProduit().getNom() %> (<%= lc.getQuantite() %>) , <%= lc.getProduit().getPrix() %><br>
            <% } %>
            <form action="<%= request.getContextPath() %>/commande" method="post" class="mt-2">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="id" value="<%= tmp.getId() %>">
                <input type="submit" class="btn btn-danger" value="Delete">
            </form>
        </li>
        <% } %>
    </ul>

    <div class="mt-5">
        <h2>Create Commande</h2>
        <form action="commande" method="post" class="mt-3">
            <input type="hidden" name="action" value="create">
            <h4>Select Client:</h4>
            <% for (Client client : clients) { %>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="selected_client" id="client_<%= client.getId() %>" value="<%= client.getId() %>">
                <label class="form-check-label" for="client_<%= client.getId() %>">
                    <%= client.getNom() %>
                </label>
            </div>
            <% } %>

            <h4 class="mt-3">Select Products for Commande:</h4>
            <% for (Produit produit : produits) { %>
            <div class="form-group">
                <label for="product_<%= produit.getId() %>"><%= produit.getNom() %></label>
                <input type="number" class="form-control" id="product_<%= produit.getId() %>" name="product_<%= produit.getId() %>" min="0" value="0">
            </div>
            <% } %>

            <button type="submit" class="btn btn-primary">Submit Order</button>
        </form>
    </div>
</div>

</body>
</html>
