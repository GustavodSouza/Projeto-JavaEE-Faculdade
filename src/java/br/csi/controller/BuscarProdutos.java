package br.csi.controller;

import br.csi.dao.ProdutosDAO;
import br.csi.model.Produto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/busca_produtos")
public class BuscarProdutos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Produto> p = new ProdutosDAO().getProdutos();
        req.setAttribute("produtos", p);
        req.getRequestDispatcher("views/listarProdutos.jsp").forward(req, resp);
    }
}
