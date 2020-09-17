<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Super mercado</title>
    </head>
    <body>
        <h1> Login :</h1> 
        <form action="logar" method="POST">
            <label for="login">Login:</label>
            <input type="email" name="login" />
            <label for="senha">Senha:</label>
            <input type="password" name="senha" />
            <button type="submit">Entrar</button>
            <br><a href="index.jsp">Voltar</a></br>
        </form>

<c:if test="${not empty mensagem}"> 
    <br><strong style="color:red;">${mensagem}</strong></br>
</c:if>        
    </body>
</html>
