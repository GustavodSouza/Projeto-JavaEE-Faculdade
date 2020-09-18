<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
<!--        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.css"> -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet">
        <link href="css/estilo.css" rel="stylesheet" />
        <title>Supermercado</title>
    </head>
    <body>
        <div class="principal">
            <span class="titulo">Projeto de POO WEB 1</span>
            <div class="formulario">
                <div class="titulo-formulario">
                    <span class="titulo-form">Formulário de login</span>
                </div>
                <div class="inputs">
                    <form method="POST" action="logar">
                        <label for="email">Email: </label>
                        <div class="input-group input-group-sm mb-3">
                            <input id="email" type="text" name="login" class="form-control" placeholder="Digite seu email" aria-describedby="inputGroup-sizing-sm">
                        </div>
                        <label for="senha">Senha: </label>
                        <div class="input-group input-group-sm mb-3">
                            <input id="senha" type="password" name="senha" class="form-control" placeholder="*********" aria-describedby="inputGroup-sizing-sm">
                        </div>
                        <div class="div-botao">
                            <button type="submit" class="btn btn-primary">Entrar</button>
                        </div>
                        <span><a href="#" >Cadastre-se</a><span>
                    </form>
                </div>
            </div>
        </div>
    <c:if test="${not empty mensagem}"> 
        <br><strong style="color:red;">${mensagem}</strong></br>
    </c:if>        
    </body>
</html>
