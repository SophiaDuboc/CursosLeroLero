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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String mensagem, String result)
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
        String mensagem;
        boolean isSuccess;
        try {
            TurmasDAO dao = new TurmasDAO();
            Turmas turma = new Turmas();
            turma.setInstrutoresId(Integer.parseInt(request.getParameter("instrutoresId")));
            turma.setCursosId(Integer.parseInt(request.getParameter("cursosId")));
            turma.setDataInicio(Date.valueOf(request.getParameter("dataInicio")));
            turma.setDataFinal(Date.valueOf(request.getParameter("dataFinal")));
            turma.setCargaHoraria(Short.parseShort(request.getParameter("carga_horaria")));

            if (request.getParameter("id") == null) {
                System.out.println("A requisicao eh para um insert");
                isSuccess = dao.insertSql(turma);
            } else {
                System.out.println("A requisicao eh para um update");
                turma.setId(Integer.parseInt(request.getParameter("id")));
                isSuccess = dao.updateSql(turma);
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
            mensagem = "erro";
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
            TurmasDAO dao = new TurmasDAO();
            Turmas turmas = new Turmas();

            boolean withCondition = false;
            if (request.getParameter("id") != null) {
                turmas.setId(Integer.parseInt(request.getParameter("id")));
                withCondition = true;
            }
            if (request.getParameter("instrutoresId") != null) {
                turmas.setInstrutoresId(Integer.parseInt(request.getParameter("instrutoresId")));
                withCondition = true;
            }
            if (request.getParameter("cursosId") != null) {
                turmas.setCursosId(Integer.parseInt(request.getParameter("cursosId")));
                withCondition = true;
            }
            if (request.getParameter("carga_horaria") != null) {
                turmas.setCargaHoraria(Short.parseShort(request.getParameter("carga_horaria")));
                withCondition = true;
            }
            if (request.getParameter("dataInicio") != null) {
                turmas.setDataInicio(Date.valueOf(request.getParameter("dataInicio")));
                withCondition = true;
            }
            if (request.getParameter("dataFinal") != null) {
                turmas.setDataFinal(Date.valueOf(request.getParameter("dataFinal")));
                withCondition = true;
            }
            if (withCondition) {
                result = dao.selectSqlWithCondidion(turmas).toJSONString();
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
            TurmasDAO dao = new TurmasDAO();
            Turmas turmas = new Turmas();
            turmas.setId(Integer.parseInt(request.getParameter("id")));
            dao.deleteSql(turmas);
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
