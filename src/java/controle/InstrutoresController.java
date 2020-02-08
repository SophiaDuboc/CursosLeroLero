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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String mensagem, String result)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InstrutoresController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InstrutoresController at " + request.getContextPath() + "</h1>");
            out.println(mensagem);
            out.println("<b>" + result + "</b>");
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
            InstrutoresDAO dao = new InstrutoresDAO();
            Instrutores instrutor = new Instrutores();
            instrutor.setNome(request.getParameter("nome"));
            instrutor.setEmail(request.getParameter("email"));
            instrutor.setValorHora(Integer.parseInt(request.getParameter("valorHora")));
            instrutor.setLogin(request.getParameter("login"));
            instrutor.setSenha(request.getParameter("senha"));
            instrutor.setExperiencia(request.getParameter("experiencia"));

            if (request.getParameter("id") == null) {
                System.out.println("A requisicao eh para um insert");
                isSuccess = dao.insertSql(instrutor);
            } else {
                System.out.println("A requisicao eh para um update");
                instrutor.setId(Integer.parseInt(request.getParameter("id")));
                isSuccess = dao.updateSql(instrutor);
            }
            if (isSuccess) {
                mensagem = "Sucesso na requisição!";
                System.out.println(mensagem);
            } else {
                mensagem = "Não foi possível completar a sua requisição.";
                System.out.println(mensagem);
            }
            processRequest(request, response, mensagem, "");
        } catch (Exception ex) {
            mensagem = "Erro nao previsto ao insrever instrutor";
            System.out.println("Erro ao gravar usuário: " + printStackTrace(ex));
            processRequest(request, response, mensagem, "");
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
        String mensagem;
        String result;
        try {
            InstrutoresDAO dao = new InstrutoresDAO();
            Instrutores instrutores = new Instrutores();

            boolean withCondition = false;
            if (request.getParameter("login") != null) {
                instrutores.setLogin(request.getParameter("login"));
                withCondition = true;
            }
            if (request.getParameter("nome") != null) {
                instrutores.setNome(request.getParameter("nome"));
                withCondition = true;
            }
            if (request.getParameter("id") != null) {
                instrutores.setId(Integer.parseInt(request.getParameter("id")));
                withCondition = true;
            }
            if (request.getParameter("valaorHora") != null) {
                instrutores.setValorHora(Integer.parseInt(request.getParameter("valorHora")));
                withCondition = true;
            }
            if (request.getParameter("email") != null) {
                instrutores.setEmail(request.getParameter("valorHora"));
                withCondition = true;
            }

            if (withCondition) {
                result = dao.selectSqlWithCondidion(instrutores);
            } else {
                result = dao.selectAllSql();
            }
            mensagem = "Select foi um sucesso";
            System.out.println(mensagem);
            processRequest(request, response, mensagem, result);
        } catch (Exception ex) {
            mensagem = "Erro";
            System.out.println(mensagem + "Erro: " + ex.getMessage());
            processRequest(request, response, mensagem, "");
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
            InstrutoresDAO dao = new InstrutoresDAO();
            Instrutores instrutores = new Instrutores();
            instrutores.setId(Integer.parseInt(request.getParameter("id")));
            dao.deleteSql(instrutores);
            System.out.println(mensagem);
            processRequest(request, response, mensagem, "");
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
    }// </editor-fold>

}
