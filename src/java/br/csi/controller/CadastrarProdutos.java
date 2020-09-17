package br.csi.controller;

import br.csi.dao.ProdutosDAO;
import br.csi.model.Produto;
import br.csi.model.Funcionario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

    @WebServlet(urlPatterns = "/cadastrar_produtos")
public class CadastrarProdutos extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Chamou GET....");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       System.out.println("Método post requisitado.....");
       PrintWriter resposta = resp.getWriter();
       
       //Aqui pega o codigo do funcionario logado para que seja cadastrado junto ao produto.
       Funcionario f =(Funcionario) req.getSession().getAttribute("usuarioLogado");
       int idFuncionario = f.getId();
      
       String descricao = req.getParameter("descricao");
       String marca = req.getParameter("marca");
       Float preco = Float.parseFloat(req.getParameter("preco"));   
       Produto produtos = new Produto(idFuncionario,descricao, marca, preco);
       
       boolean retorno = new ProdutosDAO().create(produtos);
       
       if(retorno)
       {
           req.setAttribute("nome_produto", descricao);
           RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/views/sucesso.jsp");
           disp.forward(req, resp);
       }
       else
       {
           resposta.println("<html><body><strong>ERRO</strong></body></html>");
       }
    }
}
