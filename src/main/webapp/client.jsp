<%@ page import="com.example.demo4.entities.Client" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: salah
  Date: 03/10/2023
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <title>Title</title>
</head>
<body>

    <div class="container">

        <div class="d-flex justify-content-between">
            <div>
                <h2 class="mt-3">List of Clients</h2>
                <% List<Client> malist = (List<Client>) request.getAttribute("clients") ;
                %>

                <% for ( Client tmp :malist
                ) {
                %>

                <ul class="list-group list-group-horizontal-sm mt-3">

                    <li class="list-group-item">
                        <%= tmp.getNom()%>
                    </li>
                    <li class="list-group-item">
                        <%= tmp.getAdresse()%>
                    </li>
                    <li class="list-group-item">
                        <a class="btn btn-danger" href="client?deleteId=<%= tmp.getId()%>">Delete</a>
                    </li>
                    <li class="list-group-item">
                        <a class="btn btn-success" href="clientUpdate.jsp?clientId=<%= tmp.getId()%>">Update</a>

                    </li>


                </ul>
                <% } %>
                <br>

            </div>

            <div>
                <h2 class="mt-3">Add a New Client</h2>
                <form action="client" method="post">
                    <div class="form-row">
                        <div class="form-group col-md-12">
                            <label for="nom">Name:</label>
                            <input class="form-control" type="text" id="nom" name="nom" required><br><br>

                            <label for="adresse">Address:</label>
                            <input class="form-control" type="text" id="adresse" name="adresse" required><br><br>

                            <input class="btn btn-primary" type="submit" value="Add Client">

                        </div>


                    </div>

                </form>

            </div>





        </div>





    </div>


</body>
</html>


