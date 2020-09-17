package br.csi.controller;

import br.csi.dao.ProdutosDAO;
import br.csi.model.Produto;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/busca_produtos")

public class BuscarProdutosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Chamou doGet do Servlet...");

        PrintWriter resposta = resp.getWriter();

        resposta.println("<html>");
        resposta.println("<body>");
        resposta.println("<h1> Estoque do Mercado!</h1>");
        resposta.println("<h2> Produtos disponiveis </h2>");
        resposta.println("<ul>");

        for (Produto p : new ProdutosDAO().getProdutos()) {
            resposta.println("<li>" + "Descrição: " + p.getDescricao() + "</li>");
            resposta.println("<li>" + "Marca: " + p.getMarca() + "</li>");
            resposta.println("<li>" + "Preco: " + p.getPreco() + " $ unidade" + "</li>");
            resposta.println("---------------------------------");
            resposta.println("<br></br>");

        }
        resposta.println("</ul>");

        resposta.println("<a href='index.jsp'>Tela inicial</a>");
        resposta.println("</body>");
        resposta.println("</html>");

    }

}
