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
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String urlDestino) throws ServletException, IOException {
        response.sendRedirect("./" + urlDestino + ".html");
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
        String urlDestino = "index";
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        try {
            if (verificaSeEAluno(login, senha)) {
                Integer id = getIdAluno(login, senha);
                session.setAttribute("tipoSession", "aluno");
                session.setAttribute("logado", "OK");
                session.setAttribute("id", id);
                urlDestino = "acesso-restrito/aluno/painel-aluno";
            } else if (verificaSeEAdm(login, senha)) {
                Integer id = getIdAdm(login, senha);
                session.setAttribute("tipoSession", "adm");
                session.setAttribute("logado", "OK");
                session.setAttribute("id", id);
                urlDestino = "acesso-restrito/admin/painel-admin";
            } else if (verificaSeEInstrutor(login, senha)) {
                Integer id = getIdInstrutor(login, senha);
                session.setAttribute("tipoSession", "instrutor");
                session.setAttribute("logado", "OK");
                session.setAttribute("id", id);
                urlDestino = "acesso-restrito/instrutor/painel-instrutor";
            } else {
                session.setAttribute("tipoSession", "intruso");
            }
            processRequest(request, response, urlDestino);
        } catch (Exception ex) {
            System.out.println("Erro nao esperado ao verificar login de usuario: " + ex.getMessage());
            processRequest(request, response, urlDestino);
        }
    }

    protected Integer getIdAluno(String login, String senha) throws SQLException {
        AlunosDAO dao = new AlunosDAO();
        Alunos aluno = new Alunos();
        aluno.setLogin(login);
        aluno.setSenha(senha);
        JSONObject jsonResp = dao.selectSqlWithCondidion(aluno);
        return (Integer) ((JSONObject) ((JSONArray) jsonResp.get("rows")).get(0)).get("id");
    }

    protected Integer getIdAdm(String login, String senha) throws SQLException {
        AdministradorDAO dao = new AdministradorDAO();
        Administrador adm = new Administrador();
        adm.setLogin(login);
        adm.setSenha(senha);
        JSONObject jsonResp = dao.selectSqlWithCondidion(adm);
        return (Integer) ((JSONObject) ((JSONArray) jsonResp.get("rows")).get(0)).get("id");
    }

    protected Integer getIdInstrutor(String login, String senha) throws SQLException {
        InstrutoresDAO dao = new InstrutoresDAO();
        Instrutores instrutor = new Instrutores();
        instrutor.setLogin(login);
        instrutor.setSenha(senha);
        JSONObject jsonResp = dao.selectSqlWithCondidion(instrutor);
        return (Integer) ((JSONObject) ((JSONArray) jsonResp.get("rows")).get(0)).get("id");
    }

    private boolean verificaSeEAluno(String login, String senha) throws SQLException {
        AlunosDAO dao = new AlunosDAO();
        Alunos aluno = new Alunos();
        aluno.setLogin(login);
        aluno.setSenha(senha);

        JSONObject jsonResp = dao.selectSqlWithCondidion(aluno);
        Integer total = (Integer) jsonResp.get("total");
        if (total == 0) {
            return false;
        }
        return true;
    }

    private boolean verificaSeEAdm(String login, String senha) throws SQLException {
        AdministradorDAO dao = new AdministradorDAO();
        Administrador adm = new Administrador();
        adm.setLogin(login);
        adm.setSenha(senha);
        JSONObject jsonResp = dao.selectSqlWithCondidion(adm);
        Integer total = (Integer) jsonResp.get("total");
        if (total == 0) {
            return false;
        }
        return true;
    }

    private boolean verificaSeEInstrutor(String login, String senha) throws SQLException, ParseException {
        InstrutoresDAO dao = new InstrutoresDAO();
        Instrutores instrutor = new Instrutores();
        instrutor.setLogin(login);
        instrutor.setSenha(senha);
        JSONObject jsonResp = dao.selectSqlWithCondidion(instrutor);
        Integer total = (Integer) jsonResp.get("total");
        if (total == 0) {
            return false;
        }
        return true;
    }

}
