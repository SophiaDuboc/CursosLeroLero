/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import aplicacao.Turmas;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import persistencia.TurmasDAO;

/**
 *
 * @author sophi
 */
@WebServlet(name = "TurmasController", urlPatterns = {"/TurmasController"})
public class TurmasController extends HttpServlet {

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
            out.println("<title>Servlet TurmasController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TurmasController at " + request.getContextPath() + "</h1>");
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
            Turmas turma = new Turmas();
            turma.setId(Integer.parseInt(request.getParameter("id")));
            turma.setInstrutoresId(Integer.parseInt(request.getParameter("instrutoresId")));
            turma.setCursosId(Integer.parseInt(request.getParameter("cursosId")));
            turma.setDataInicio(Date.valueOf(request.getParameter("dataInicio")));
            turma.setDataFinal(Date.valueOf(request.getParameter("dataFinal")));
            turma.setCargaHoraria(Short.parseShort(request.getParameter("cargaHoraria")));

            TurmasDAO dao = new TurmasDAO();

            if (dao.insertSql(turma)) {
                mensagem = "Turma criada com sucesso";
            } else {
                mensagem = "Turma ja existe";
            }

        } catch (Exception ex) {
            mensagem = "erro";
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
