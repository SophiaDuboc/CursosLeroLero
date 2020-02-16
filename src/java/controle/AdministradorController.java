/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import aplicacao.Administrador;
import java.io.IOException;
import java.io.PrintWriter;
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String result)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println(result);
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
        String result = "";
        boolean isSuccess;
        try {
            AdministradorDAO dao = new AdministradorDAO();
            Administrador adm = new Administrador();
            adm.setLogin(request.getParameter("username"));
            adm.setSenha((String) request.getAttribute("password"));
            adm.setNome(request.getParameter("name"));

            if (request.getParameter("id") == null) {
                System.out.println("A requisicao eh para um insert");
                isSuccess = dao.insertSql(adm);
                result = "Inserido ";
            } else {
                System.out.println("A requisicao eh para um update");
                adm.setId(Integer.parseInt(request.getParameter("id")));
                isSuccess = dao.updateSql(adm);
                result = "Update ";
            }
            if (isSuccess) {
                System.out.println(result);
            } else {
                System.out.println(result);
            }
            result += "com sucesso";
            processRequest(request, response, result);
        } catch (Exception ex) {
            result = " com erro";
            System.out.println("Erro ao inserir adm: " + ex.getMessage());
            processRequest(request, response, result);
        }
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
        String result = "";
        try {
            AdministradorDAO dao = new AdministradorDAO();
            Administrador adm = new Administrador();

            boolean withCondition = false;
            if (request.getParameter("login") != null) {
                adm.setLogin(request.getParameter("login"));
                withCondition = true;
            }
            if (request.getParameter("nome") != null) {
                adm.setNome(request.getParameter("nome"));
                withCondition = true;
            }
            if (request.getParameter("id") != null) {
                adm.setId(Integer.parseInt(request.getParameter("id")));
                withCondition = true;
            }
            if (withCondition) {
                result = dao.selectSqlWithCondidion(adm).toJSONString();
            } else {
                result = dao.selectAllSql().toJSONString();
            }
            result = "Select foi um sucesso";
            System.out.println(result);
            processRequest(request, response, result);
        } catch (Exception ex) {
            result = "Erro";
            System.out.println(result + ": " + ex.getMessage());
            processRequest(request, response, result);
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
        String result;
        try {
            AdministradorDAO dao = new AdministradorDAO();
            Administrador adm = new Administrador();
            adm.setId(Integer.parseInt(request.getParameter("id")));
            dao.deleteSql(adm);
            result = "Deletado com sucesso";
            System.out.println(result);
            processRequest(request, response, result);
        } catch (Exception ex) {
            result = "Erro o deletar";
            System.err.println(result + ex.getMessage());
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
