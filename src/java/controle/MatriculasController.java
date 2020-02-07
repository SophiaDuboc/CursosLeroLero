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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String mensagem)
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
        try {
            Matriculas mat = new Matriculas();
            mat.setId(Integer.parseInt(request.getParameter("id")));
            mat.setTurmaId(Integer.parseInt(request.getParameter("turmaId")));
            mat.setAlunoId(Integer.parseInt(request.getParameter("alunoId")));
            mat.setDate(Date.valueOf(request.getParameter("dataMatricula")));
            mat.setNota(Double.parseDouble(request.getParameter("nota")));
            
            MatriculasDAO dao = new MatriculasDAO();
            
            if (dao.insertSql(mat)) {
                mensagem = "Aluno matriculado com sucesso";
            } else {
                mensagem = "Aluno ja matriculado";
                System.out.println(mensagem);
            }
            
        } catch (Exception ex) {
            mensagem = "Erro";
            System.out.println(mensagem + " : " + ex.getMessage());
        }
        processRequest(request, response, mensagem);
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
