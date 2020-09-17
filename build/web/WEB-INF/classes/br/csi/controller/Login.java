package br.csi.controller;

import br.csi.dao.FuncionarioDAO;
import br.csi.dao.LoginDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/logar")
public class Login extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       
        String login = req.getParameter("login");
        String senha = req.getParameter("senha");
        
        System.err.println(login +" - " + senha);
        
        boolean autenticado = 
                new LoginDAO().autenticar(login, senha);
        
        RequestDispatcher disp;
        
        if(autenticado){
           
            HttpSession sessao = req.getSession();
 sessao.setAttribute("usuarioLogado", 
         new FuncionarioDAO().read(login, senha));
 
 
            disp = req.getRequestDispatcher("WEB-INF/views/menu.jsp");
            disp.forward(req, resp); 
        }else{ 
            req.setAttribute("mensagem", "Usuário ou Senha INCORRETOS");
            disp = req.getRequestDispatcher("login.jsp");
            disp.forward(req, resp);            
        }
        
    }

    
    
}