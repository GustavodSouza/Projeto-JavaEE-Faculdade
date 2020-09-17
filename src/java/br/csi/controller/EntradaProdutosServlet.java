package br.csi.controller;

import br.csi.dao.EntradaDAO;
import br.csi.dao.EstoqueDAO;
import br.csi.model.Entrada;
import br.csi.model.Estoque;
import br.csi.model.Funcionario;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "entradaProdutos")
public class EntradaProdutosServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Método post requisitado.....");
        PrintWriter resposta = resp.getWriter();

        //Aqui pega o codigo do funcionario logado para que seja cadastrado junto ao produto.
        Funcionario f =(Funcionario) req.getSession().getAttribute("usuarioLogado");
        int idFuncionario = f.getId();  
        
        //Pega o id do produto selecionado na jsp.
        int idProduto = Integer.parseInt(req.getParameter("cod"));
        
        //Pegar a data e hora do sistema.
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatterDataHora = DateTimeFormatter.ofPattern("dd-MM-uuu HH:mm:ss");
        String data_entrada = formatterDataHora.format(agora);

        //Pega a quantidade.
        int quantidade = Integer.parseInt(req.getParameter("quantidade"));
        
        //Insere o produto na entrada.
        Entrada entrada = new Entrada(idProduto, idFuncionario, data_entrada, quantidade);
       
        //Busca o produto a partir do Id recebido do jsp
        Estoque ret = new EstoqueDAO().read(idProduto);
        
        //Se o retorno for diferente de NULL o produto ja existe.
        //Dar update apenas
        if(ret != null)
        {
            Estoque est = new Estoque();
            est.setIdProduto(idProduto);
            est.setQuantidade(quantidade);
            new EstoqueDAO().updateEntrada(est);
        }
        //Se nao cria o produto no estoque.
        else
        {
            //Insere o produto no estoque
             Estoque estoque = new Estoque(idProduto, quantidade);
             new EstoqueDAO().create(estoque);
        }

        boolean retorno = new EntradaDAO().create(entrada);

        if (retorno) {
            RequestDispatcher disp = req.getRequestDispatcher("WEB-INF/views/sucessoEntrada.jsp");
            disp.forward(req, resp);
        } else {
            resposta.println("<html><body><strong>ERRO</strong></body></html>");
        }

        
    }
}
