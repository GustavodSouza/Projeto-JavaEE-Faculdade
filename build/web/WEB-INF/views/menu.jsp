<%-- 
    Document   : menu
    Created on : 06/11/2018, 23:14:20
    Author     : Error404
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Supermercado</title>
    </head>
    <body>
        <h2> Bem-Vindo: ${sessionScope['usuarioLogado'].nome} </h2>  
        <a href="cadastrar.jsp">Cadastrar Produtos</a>
        <br><a href="http://localhost:8080/estoque_mercado/dispEntradaServlet">Entrada de Produtos</a></br>
        <a href="http://localhost:8080/estoque_mercado/dispSaidaServlet">Saida de Produtos</a>
        <br><a href="http://localhost:8080/estoque_mercado/relatorioEntrada" >Gerar relatório de entrada</a></br>
        <a href="http://localhost:8080/estoque_mercado/relatorioSaida" >Gerar relatório de saida</a>
        <br><a href="http://localhost:8080/estoque_mercado/relatorioEstoque">Relatorio do estoque</a></br>
        <br><br><a href="logout">Logout</a></br></br>
    </body>
</html>
