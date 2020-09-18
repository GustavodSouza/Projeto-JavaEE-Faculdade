package br.csi.controller;

import br.csi.dao.EstoqueDAO;
import br.csi.dao.ProdutosDAO;
import br.csi.dao.SaidaDAO;
import br.csi.model.Estoque;
import br.csi.model.Funcionario;
import br.csi.model.Produto;
import br.csi.model.Saida;
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

@WebServlet(urlPatterns = "/saidaProdutos")
public class SaidaProdutos extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Método post requisitado.....");
        PrintWriter resposta = resp.getWriter();

        //Aqui pega o codigo do funcionario logado para que seja cadastrado junto ao produto.
        Funcionario f = (Funcionario) req.getSession().getAttribute("usuarioLogado");
        int idFuncionario = f.getId();

        int idProduto = Integer.parseInt(req.getParameter("cod"));

        //Pegar a data e hora do sistema.
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatterDataHora = DateTimeFormatter.ofPattern("dd-MM-uuu HH:mm:ss");
        String data_saida = formatterDataHora.format(agora);

        //Pega a quantidade.
        int quantidade = Integer.parseInt(req.getParameter("quantidade"));

        //Insere o produto na entrada.
        Saida saida = new Saida(idProduto, idFuncionario, data_saida, quantidade);

        //Busca o produto a partir do Id recebido do jsp
        Estoque ret = new EstoqueDAO().read(idProduto);

        //Se o retorno for diferente de NULL o produto ja existe.
        //Dar update apenas
        if (ret != null) {
            Estoque est = new Estoque();
            est.setIdProduto(idProduto);
            est.setQuantidade(quantidade);
            new EstoqueDAO().updateSaida(est);
        } //Se nao cria o produto no estoque.
        else {
            //Insere o produto no estoque
            Estoque estoque = new Estoque(idProduto, quantidade);
            new EstoqueDAO().create(estoque);
        }

        boolean retorno = new SaidaDAO().create(saida);

        if (retorno) {
            RequestDispatcher disp = req.getRequestDispatcher("views/sucessoSaida.jsp");
            disp.forward(req, resp);
        } else {
            resposta.println("<html><body><strong>ERRO</strong></body></html>");
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Produto> p = new ProdutosDAO().getProdutos();
        req.setAttribute("produtos", p);
        req.getRequestDispatcher("views/saida.jsp").forward(req, resp);
    }
}
