package br.csi.controller;

import br.csi.dao.EntradaDAO;
import br.csi.dao.SaidaDAO;
import br.csi.model.Entrada;
import br.csi.model.Saida;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = "/relatorioSaida")
public class RelatórioSaidaServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("Chamou doGet do Servlet...");

        PrintWriter resposta = resp.getWriter();

        resposta.println("<html>");
        resposta.println("<body>");
        resposta.println("<h1> Relatório de entrada</h1>");
        resposta.println("<ul>");

        for (Saida s : new SaidaDAO().getSaida()) {
            resposta.println("<li>" + "CodProduto: " + s.getIdProduto() + "</li>");
            resposta.println("<li>" + "Funcionario: " + s.getIdFuncionario() + "</li>");
            resposta.println("<li>" + "Data/Hora: " + s.getData_saida() + "</li>");
            resposta.println("<li>" + "Quantidade: " + s.getQuantidade() + "</li>");
            resposta.println("---------------------------------");
            resposta.println("<br></br>");

        }
        resposta.println("</ul>");

        resposta.println("<a href='index.jsp'>Tela inicial</a>");
        resposta.println("</body>");
        resposta.println("</html>");

    }
}
