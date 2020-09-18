package br.csi.controller;

import br.csi.dao.EntradaDAO;
import br.csi.dao.EstoqueDAO;
import br.csi.dao.ProdutosDAO;
import br.csi.model.Entrada;
import br.csi.model.Estoque;
import br.csi.model.Produto;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/entradaProdutos")
public class EntradaProdutos extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter resposta = resp.getWriter();
        
        int idProduto = Integer.parseInt(req.getParameter("cod"));
        
        //Pegar a data e hora do sistema.
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatterDataHora = DateTimeFormatter.ofPattern("dd-MM-uuu HH:mm:ss");
        String data_entrada = formatterDataHora.format(agora);

        //Pega a quantidade.
        int quantidade = Integer.parseInt(req.getParameter("quantidade"));
        
        //Insere o produto na entrada.
        Entrada entrada = new Entrada(idProduto, 1, data_entrada, quantidade);
       
        //Busca o produto a partir do Id recebido do jsp
        Estoque ret = new EstoqueDAO().read(idProduto);
        
        //Se o retorno for diferente de NULL o produto ja existe.
        //Dar update apenas
        if(ret != null) {
            Estoque est = new Estoque();
            est.setIdProduto(idProduto);
            est.setQuantidade(quantidade);
            new EstoqueDAO().updateEntrada(est);
            resposta.println("<html><body><strong>Ja existe</strong></body></html>");
        } else {
             Estoque estoque = new Estoque(idProduto, quantidade);
             new EstoqueDAO().create(estoque);
        }

        boolean retorno = new EntradaDAO().create(entrada);

        if (retorno) {
            RequestDispatcher disp = req.getRequestDispatcher("views/sucessoEntrada.jsp");
            disp.forward(req, resp);
        } else {
            resposta.println("<html><body><strong>ERRO</strong></body></html>");
        }

        
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Produto> p = new ProdutosDAO().getProdutos();
        req.setAttribute("produtos", p);
        req.getRequestDispatcher("views/entrada.jsp").forward(req, resp);
    }
}
