/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import aplicacao.Administrador;
import aplicacao.Alunos;
import aplicacao.Instrutores;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import persistencia.AdministradorDAO;
import persistencia.AlunosDAO;
import persistencia.InstrutoresDAO;

/**
 *
 * @author sophi
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        try {
            if (verificaSeEAluno(login, senha)) {
                session.setAttribute("tipoSession", "aluno");
                session.setAttribute("logado", "OK");

            } else if (verificaSeEAdm(login, senha)) {
                session.setAttribute("tipoSession", "adm");
                session.setAttribute("logado", "OK");
            } else if (verificaSeEInstrutor(login, senha)) {
                session.setAttribute("tipoSession", "instrutor");
                session.setAttribute("logado", "OK");
            } else {
                session.setAttribute("tipoSession", "intruso");
            }
        } catch (Exception ex) {

        }

        processRequest(request, response);
    }

    private boolean verificaSeEAluno(String login, String senha) throws SQLException {
        AlunosDAO dao = new AlunosDAO();
        Alunos aluno = new Alunos();
        aluno.setLogin(login);
        aluno.setSenha(senha);

        if (dao.selectSqlWithCondidion(aluno).equals("")) {
            return false;
        }
        return true;
    }

    private boolean verificaSeEAdm(String login, String senha) throws SQLException {
        AdministradorDAO dao = new AdministradorDAO();
        Administrador adm = new Administrador();
        adm.setLogin(login);
        adm.setSenha(senha);
        if (dao.selectSqlWithCondidion(adm).equals("")) {
            return false;
        }
        return true;
    }

    private boolean verificaSeEInstrutor(String login, String senha) throws SQLException {
        InstrutoresDAO dao = new InstrutoresDAO();
        Instrutores instrutor = new Instrutores();
        instrutor.setLogin(login);
        instrutor.setSenha(senha);
        if (dao.selectSqlWithCondidion(instrutor).equals("")) {
            return false;
        }
        return true;
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
