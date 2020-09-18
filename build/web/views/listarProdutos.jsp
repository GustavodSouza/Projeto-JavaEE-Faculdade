<%-- 
    Document   : listarProdutos
    Created on : 18/09/2020, 09:55:14
    Author     : gustavosouza
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.css"> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">

        <title>Estoque Mercado 2.0</title>
    </head>
    <body>
        <h1>Lista de produtos disponíveis</h1>
        <a class="btn-floating btn-large waves-effect waves-light red"><i class="material-icons">add</i></a>
       <c:forEach items="${produtos}" var="item" >
            <option value="${item.getId()}">
                ${item.getDescricao()}
            </option> 
        </c:forEach>
    </body>
</html>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
