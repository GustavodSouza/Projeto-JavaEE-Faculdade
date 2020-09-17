<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<html>
    <head>
        <title>Supermercado.com</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1> Usuario Logado: ${sessionScope['usuarioLogado'].nome}</h1>
        <div><h1>Cadastrar Produto</h1></div>
        <form action="cadastrar_produtos" method="POST">
            <p>Descrição: <input name="descricao" type="text" placeholder="Descrição do produto"></p>
            <p>Marca: <input name="marca" type="text" placeholder="Marca do produto"></p>
            <p>Preco: <input name="preco" type="text" placeholder="Preço por unidade"></p>
            <input type="submit" value="Cadastrar">
        </form>
        <a href="busca_produtos">Visualizar estoque</a>
        <br><a href="index.jsp">Pagina Inicial</a></br>
    </body>
</html>

