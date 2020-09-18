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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Estoque Mercado 2.0</title>
    </head>
    <body>
        <h1>Lista de produtos disponíveis</h1>
       <c:forEach items="${produtos}" var="item" >
            <option value="${item.getId()}">
                ${item.getDescricao()}
            </option> 
        </c:forEach>
    </body>
</html>
