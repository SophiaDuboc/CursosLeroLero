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
@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String result) throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            out.println(result);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = "Problemas ao pegar informações do usuário";
        HttpSession session = request.getSession(false);
        if (session != null) {
            if (session.getAttribute("logado").equals("OK")) {
                try {
                    if (session.getAttribute("tipoSession").equals("aluno")) {
                        result = getAluno((int) session.getAttribute("id"));
                    } else if (session.getAttribute("tipoSession").equals("adm")) {
                        result = getAdm((int) session.getAttribute("id"));
                    } else if (session.getAttribute("tipoSession").equals("instrutor")) {
                        result = getInstrutor((int) session.getAttribute("id"));
                    }
                } catch (Exception ex) {
                    System.err.println("Erro ao pegar as informações do usuário: " + ex);
                }
            }
        }
        processRequest(request, response, result);
    }

    protected String getAluno(int id) throws SQLException {
        AlunosDAO dao = new AlunosDAO();
        Alunos aluno = new Alunos();
        aluno.setId(id);
        return (dao.selectSqlWithCondidion(aluno)).toJSONString();
    }

    protected String getAdm(int id) throws SQLException {
        AdministradorDAO dao = new AdministradorDAO();
        Administrador adm = new Administrador();
        adm.setId(id);
        return (dao.selectSqlWithCondidion(adm)).toJSONString();
    }

    protected String getInstrutor(int id) throws SQLException {
        InstrutoresDAO dao = new InstrutoresDAO();
        Instrutores instrutor = new Instrutores();
        instrutor.setId(id);
        return (dao.selectSqlWithCondidion(instrutor)).toJSONString();
    }

}
