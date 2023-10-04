<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Produits</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container mt-4">

    <!-- Section Header -->
    <div class="d-flex justify-content-between align-items-center">
        <h2>Liste de Produits</h2>
    </div>

    <!-- List of Produits -->
    <div class="mt-4">
        <% java.util.List<com.example.demo4.entities.Produit> maListe = (java.util.List<com.example.demo4.entities.Produit>) request.getAttribute("produits");
            for (com.example.demo4.entities.Produit p : maListe) {
        %>
        <div class="card mb-2">
            <div class="card-body">
                <div class="d-flex justify-content-between align-items-center">
                    <span><strong>Nom:</strong> <%= p.getNom() %> | <strong>Prix:</strong> <%= p.getPrix() %></span>
                    <div>
                        <a href="produit?deleteId=<%= p.getId() %>" class="btn btn-danger btn-sm">Supprimer</a>
                        <a href="produitUpdate.jsp?updateId=<%= p.getId() %>" class="btn btn-success btn-sm">Modifier</a>
                    </div>
                </div>
            </div>
        </div>
        <% } %>
    </div>

    <!-- Form for Adding a New Produit -->
    <div class="mt-5">
        <h4>Ajouter Nouveau Produit</h4>
        <form action="produit" method="POST">
            <div class="form-group">
                <label for="nom">Nom:</label>
                <input type="text" name="nom" id="nom" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="prix">Prix:</label>
                <input type="text" name="prix" id="prix" class="form-control" required>
            </div>
            <button type="submit" class="btn btn-primary">Ajouter Produit</button>
        </form>
    </div>
</div>
</body>
</html>
