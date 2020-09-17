package br.csi.controller;

import br.csi.dao.ProdutosDAO;
import br.csi.model.Produto;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/entrada_servlet")
public class EntradaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Produto> p = new ProdutosDAO().getProdutos();
        
        p.forEach((x) -> System.out.println(x.getDescricao()));

        req.setAttribute("produtos", p);
        req.getRequestDispatcher("/WEB-INF/views/entrada.jsp").forward(req, resp);
    }
}
