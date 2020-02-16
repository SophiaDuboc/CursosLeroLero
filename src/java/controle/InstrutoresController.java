/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import aplicacao.Instrutores;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static jdk.nashorn.internal.objects.NativeError.printStackTrace;
import persistencia.InstrutoresDAO;

/**
 *
 * @author sophi
 */
@WebServlet(name = "InstrutoresController", urlPatterns = {"/InstrutoresController"})
public class InstrutoresController extends HttpServlet {

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
            InstrutoresDAO dao = new InstrutoresDAO();
            Instrutores instrutor = new Instrutores();
            instrutor.setNome(request.getParameter("nome_completo"));
            instrutor.setEmail(request.getParameter("your_email"));
            instrutor.setValorHora(Integer.parseInt(request.getParameter("valorInt")));
            instrutor.setLogin(request.getParameter("username"));
            if (request.getAttribute("password") != null) {
                instrutor.setSenha((String) request.getAttribute("password"));
            }
            instrutor.setExperiencia(request.getParameter("experiencia"));

            if (request.getParameter("id") == null) {
                System.out.println("A requisicao eh para um insert");
                response.sendRedirect("http://localhost:8080/CursosLeroLero/acesso-restrito/admin/bootgrid-instrutores.html");
                isSuccess = dao.insertSql(instrutor);
                result = "Inserido ";
            } else {
                System.out.println("A requisicao eh para um update");
                instrutor.setId(Integer.parseInt(request.getParameter("id")));
                isSuccess = dao.updateSql(instrutor);
                result = "Update  ";
            }
            if (isSuccess) {
                result += "com sucesso";
                System.out.println(result);
            } else {
                result += "com erro";
                System.out.println(result);
            }
            processRequest(request, response, result);
        } catch (Exception ex) {
            result = "com problemas";
            System.out.println("Erro ao gravar usuário: " + printStackTrace(ex));
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
            InstrutoresDAO dao = new InstrutoresDAO();
            Instrutores instrutores = new Instrutores();

            boolean withCondition = false;
            if (request.getParameter("username") != null) {
                instrutores.setLogin(request.getParameter("username"));
                withCondition = true;
            }
            if (request.getParameter("nome_completo") != null) {
                instrutores.setNome(request.getParameter("nome_completo"));
                withCondition = true;
            }
            if (request.getParameter("id") != null) {
                instrutores.setId(Integer.parseInt(request.getParameter("id")));
                withCondition = true;
            }
            if (request.getParameter("money") != null) {
                instrutores.setValorHora(Integer.parseInt(request.getParameter("money")));
                withCondition = true;
            }
            if (request.getParameter("your_email") != null) {
                instrutores.setEmail(request.getParameter("your_email"));
                withCondition = true;
            }
            if (withCondition) {
                result = dao.selectSqlWithCondidion(instrutores).toJSONString();
            } else {
                result = dao.selectAllSql().toJSONString();
            }
            System.out.println("Select foi um sucesso");
            processRequest(request, response, result);
        } catch (Exception ex) {
            result = "Erro";
            System.out.println("Erro: " + ex.getMessage());
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
        String result = "Deletado ";
        try {
            InstrutoresDAO dao = new InstrutoresDAO();
            Instrutores instrutores = new Instrutores();
            instrutores.setId(Integer.parseInt(request.getParameter("id")));
            dao.deleteSql(instrutores);
            result += "com sucesso";
            System.out.println(result);
            processRequest(request, response, result);
        } catch (Exception ex) {
            result += "com erro";
            System.err.println("Erro: " + ex.getMessage());
            processRequest(request, response, result);
        }
    }
}
