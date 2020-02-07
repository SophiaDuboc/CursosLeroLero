/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import aplicacao.Administrador;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persistencia.AdministradorDAO;

/**
 *
 * @author sophi
 */
@WebServlet(name = "AdministradorController", urlPatterns = {"/AdministradorController"})
public class AdministradorController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String mensagem)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdministradorController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdministradorController at " + request.getContextPath() + "</h1>");
            out.println(mensagem);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mensagem;
        boolean isSuccess;
        try {
            AdministradorDAO dao = new AdministradorDAO();

            Administrador adm = new Administrador();
            adm.setLogin(request.getParameter("username"));
            adm.setSenha(request.getParameter("password"));
            adm.setNome(request.getParameter("name"));

            if (request.getParameter("id") == null) {
                System.out.println("A requisicao eh para um insert");
                isSuccess = dao.insertSql(adm);
            } else {
                System.out.println("A requisicao eh para um update");
                adm.setId(Integer.parseInt(request.getParameter("id")));
                isSuccess = dao.updateSql(adm);
            }

            if (isSuccess) {
                mensagem = "Sucesso na requisição!";
                System.out.println(mensagem);
            } else {
                mensagem = "Não foi possível completar a sua requisição.";
                System.out.println(mensagem);
            }

        } catch (Exception ex) {
            mensagem = "Problema não esperado ao inserir adm";
            System.out.println("Erro ao inserir adm: " + ex.getMessage());
        }
        processRequest(request, response, mensagem);
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mensagem;
        try {
            AdministradorDAO dao = new AdministradorDAO();
            Administrador adm = new Administrador();

            adm.setLogin(request.getParameter("login"));
            adm.setSenha(request.getParameter("senha"));
            adm.setNome(request.getParameter("nome"));

            List<Object> result = dao.selectAllSql();
            mensagem = result.toString();
            System.out.println("Select com sucesso");
            processRequest(request, response, mensagem);
        } catch (Exception ex) {
            mensagem = "Erro";
            System.out.println(mensagem);
        }
    }

    /**
     * Handles the HTTP <code>DELET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mensagem;
        try {
            mensagem = "sucesso";
            AdministradorDAO dao = new AdministradorDAO();
            Administrador adm = new Administrador();
            adm.setId(Integer.parseInt(request.getParameter("id")));
            dao.deleteSql(adm);
            System.out.println(mensagem);
            processRequest(request, response, mensagem);
        } catch (Exception ex) {
            mensagem = "erro";
            System.err.println(mensagem + "erro: " + ex.getMessage());
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
