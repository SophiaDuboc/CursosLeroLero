/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import aplicacao.Matriculas;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persistencia.MatriculasDAO;

/**
 *
 * @author sophi
 */
@WebServlet(name = "MatriculasController", urlPatterns = {"/MatriculasController"})
public class MatriculasController extends HttpServlet {

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
            out.println("<title>Servlet MatriculasController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MatriculasController at " + request.getContextPath() + "</h1>");
            out.println(mensagem);
            out.println("<p>" + result + "</p>");
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
            MatriculasDAO dao = new MatriculasDAO();
            Matriculas mat = new Matriculas();
            mat.setTurmaId(Integer.parseInt(request.getParameter("turmaId")));
            mat.setAlunoId(Integer.parseInt(request.getParameter("alunoId")));
            mat.setDate(Date.valueOf(request.getParameter("dataMatricula")));
            mat.setNota(Double.parseDouble(request.getParameter("nota")));

            if (request.getParameter("id") == null) {
                System.out.println("A requisicao eh para um insert");
                isSuccess = dao.insertSql(mat);
            } else {
                System.out.println("A requisicao eh para um update");
                mat.setId(Integer.parseInt(request.getParameter("id")));
                isSuccess = dao.updateSql(mat);
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
            mensagem = "Erro";
            System.out.println(mensagem + " : " + ex.getMessage());
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
            MatriculasDAO dao = new MatriculasDAO();
            Matriculas mat = new Matriculas();

            boolean withCondition = false;
            if (request.getParameter("id") != null) {
                mat.setId(Integer.parseInt(request.getParameter("id")));
                withCondition = true;
            }
            if (request.getParameter("turmaId") != null) {
                mat.setTurmaId(Integer.parseInt(request.getParameter("turmaId")));
                withCondition = true;
            }
            if (request.getParameter("alunoId") != null) {
                mat.setAlunoId(Integer.parseInt(request.getParameter("alunoId")));
                withCondition = true;
            }
            if (request.getParameter("dataMatricula") != null) {
                mat.setDate(Date.valueOf(request.getParameter("dataMatricula")));
                withCondition = true;
            }
            if (request.getParameter("note") != null) {
                mat.setNota(Double.parseDouble(request.getParameter("nota")));
                withCondition = true;
            }
            if (withCondition) {
                result = dao.selectSqlWithCondidion(mat).toJSONString();
            } else {
                result = dao.selectAllSql().toJSONString();
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
            MatriculasDAO dao = new MatriculasDAO();
            Matriculas mat = new Matriculas();
            mat.setId(Integer.parseInt(request.getParameter("id")));
            dao.deleteSql(mat);
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
