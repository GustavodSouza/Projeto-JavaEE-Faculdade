<%@page import="br.csi.model.Produto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.csi.dao.ProdutosDAO"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.css"> 
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">
        <link href="css/estilo.css" rel="stylesheet" />
        <title>Supermercado</title>
    </head>
    <body>
        <div class="principal">
            <span class="titulo">Projeto de POO WEB 1</span>
            <div class="botoes">
                <div class="btn-login">
                    <a href="login.jsp" class="waves-effect waves-light btn"><i class="material-icons left">person</i>Efetuar Login</a>
                </div>
                <div class="btn-produtos">
                    <a href="busca_produtos" class="waves-effect waves-light btn"><i class="material-icons left">local_grocery_store</i>Produtos Disponíveis</a>
                </div>
            </div>
        </div>
    </body>
</html>