package br.csi.controller;

import br.csi.dao.EntradaDAO;
import br.csi.dao.EstoqueDAO;
import br.csi.model.Entrada;
import br.csi.model.Estoque;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "relatorioEstoque")
public class RelatórioEstoque extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Chamou doGet do Servlet...");

        PrintWriter resposta = resp.getWriter();

        resposta.println("<html>");
        resposta.println("<body>");
        resposta.println("<h1> Relatório de estoque</h1>");
        resposta.println("<ul>");

        for (Estoque e : new EstoqueDAO().getEstoque()) {
            resposta.println("<li>" + "CodProduto: " + e.getIdProduto() + "</li>");
            resposta.println("<li>" + "Quantidade: " + e.getQuantidade() + "</li>");
            resposta.println("---------------------------------");
            resposta.println("<br></br>");

        }
        resposta.println("</ul>");

        resposta.println("</body>");
        resposta.println("</html>");

    }
}
