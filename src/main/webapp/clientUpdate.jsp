        <%@ page import="com.example.demo4.entities.Client,com.example.demo4.service.ClientRepository" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Client</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</head>
<body>

<%
    String clientIdStr = request.getParameter("clientId");
    Client client = null;

    Long clientId = null;
    try {
        ClientRepository clientRepository1 = new ClientRepository();
        clientId = Long.parseLong(clientIdStr);
        client = clientRepository1.getOneClient(clientId);
    } catch (NumberFormatException e) {
    } catch (SQLException | ClassNotFoundException e) {
    }

    if (client == null) {
    }
%>

<div class="container">
    <h2 class="mt-3">Update Client: <%=client.getNom()%></h2>
    <form action="client" method="post">
        <div class="form-row">
            <div class="form-group col-md-12">
                <input  type="hidden" name="action" value="update">
                <input type="hidden" name="clientId" value="<%=clientId%>">
                nom: <input class="form-control" type="text" name="nom" value="<%=client.getNom()%>"><br>
                adresse: <input class="form-control" type="text" name="adresse" value="<%=client.getAdresse()%>"><br>
                <input class="btn btn-primary" type="submit" value="Update Client">

            </div>


        </div>
    </form>


</div>

</body>
</html>
