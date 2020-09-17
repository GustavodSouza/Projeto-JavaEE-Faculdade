<%-- 
    Document   : entrada.jsp
    Created on : 05/11/2018, 15:28:40
    Author     : Aluno
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Supermercado</title>
    </head>
    <body>
        <h1> Entrada de Produtos </h1>
        <h2> Usuario Logado: ${sessionScope['usuarioLogado'].nome} </h2>
        <form action="entradaProdutos" method="POST">
            
            <%-- Pega o id do produto selecionado--%>
            <select name="cod">        
                <c:forEach items="${produtos}" var="item" >
                    <option value="${item.getId()}">
                        ${item.getDescricao()}
                    </option> 
                </c:forEach>
                <br><br><label for="quantidade">Quantidade:</label></br></br>
                <input type="number" name="quantidade" placeholder="Insira a quantidade" >
                <input type="submit" value="ok" >
            </select>
        </form>
    </body>
</html>
