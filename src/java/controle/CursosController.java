/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import aplicacao.Cursos;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persistencia.CursosDAO;

/**
 *
 * @author sophi
 */
@WebServlet(name = "CursosController", urlPatterns = {"/CursosController"})
public class CursosController extends HttpServlet {

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
            out.println("<title>Servlet CursosController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CursosController at " + request.getContextPath() + "</h1>");
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
            Cursos curso = new Cursos();
            curso.setId(Integer.parseInt(request.getParameter("id")));
            curso.setNome(request.getParameter("nome"));
            curso.setRequisito(request.getParameter("requisito"));
            curso.setEmenta(request.getParameter("ementa"));
            curso.setCargaHoraria(Short.parseShort(request.getParameter("cargaHoraria")));
            curso.setPreco(Double.parseDouble(request.getParameter("preco")));

            CursosDAO dao = new CursosDAO();

            if (dao.insertSql(curso)) {
                mensagem = "Curso inserido com sucesso";
                System.out.println(mensagem);
            } else {
                mensagem = "Curso ja existe";
            }

        } catch (Exception ex) {
            mensagem = "Problemas ao adicionar curso";
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
