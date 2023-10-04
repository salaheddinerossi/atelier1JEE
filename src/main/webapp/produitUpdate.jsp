<%@ page import="com.example.demo4.entities.Produit,com.example.demo4.service.ProduitRepository" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Produit</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>

<%
    String produitIdStr = request.getParameter("updateId");
    Produit produit = null;
    Long produitId = null;
    try {
        ProduitRepository produitRepository1 = new ProduitRepository();
        produitId = Long.parseLong(produitIdStr);
        produit = produitRepository1.getOneProduit(produitId);
    } catch (NumberFormatException | SQLException e) {
        e.printStackTrace();
    }
%>

<div class="container mt-5">
    <h2>Update Produit: <%= produit.getNom() %></h2>
    <form action="produit" method="POST" class="mt-3">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="produitId" value="<%= produitId %>">
        <div class="form-group">
            <label for="nom">Nom:</label>
            <input type="text" class="form-control" id="nom" name="nom" value="<%= produit.getNom() %>">
        </div>
        <div class="form-group">
            <label for="prix">Prix:</label>
            <input type="text" class="form-control" id="prix" name="prix" value="<%= produit.getPrix() %>">
        </div>
        <button type="submit" class="btn btn-primary">Update Produit</button>
    </form>
</div>

</body>
</html>
