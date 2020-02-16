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
            CursosDAO dao = new CursosDAO();
            Cursos curso = new Cursos();
            curso.setNome(request.getParameter("nome_curso"));
            curso.setRequisito(request.getParameter("requisitos"));
            curso.setEmenta(request.getParameter("ementa"));
            curso.setCargaHoraria(Short.parseShort(request.getParameter("carga_horaria")));
            curso.setPreco(Double.parseDouble(request.getParameter("money").replaceAll("\\.", "").replaceAll(",", ".")));

            if (request.getParameter("id") == null) {
                System.out.println("A requisicao eh para um insert");
                isSuccess = dao.insertSql(curso);
            } else {
                System.out.println("A requisicao eh para um update");
                curso.setId(Integer.parseInt(request.getParameter("id")));
                isSuccess = dao.updateSql(curso);
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
            mensagem = "Problemas ao adicionar curso";
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
            CursosDAO dao = new CursosDAO();
            Cursos curso = new Cursos();

            boolean withCondition = false;
            if (request.getParameter("id") != null) {
                curso.setId(Integer.parseInt(request.getParameter("id")));
                withCondition = true;
            }
            if (request.getParameter("nome") != null) {
                curso.setNome(request.getParameter("nome"));
                withCondition = true;
            }
            if (request.getParameter("requisito") != null) {
                curso.setRequisito(request.getParameter("requisito"));
                withCondition = true;
            }
            if (request.getParameter("ementa") != null) {
                curso.setEmenta(request.getParameter("ementa"));
                withCondition = true;
            }
            if (request.getParameter("carga_horaria") != null) {
                curso.setCargaHoraria(Short.parseShort(request.getParameter("carga_horaria")));
                withCondition = true;
            }
            if (request.getParameter("money") != null) {
                curso.setPreco(Double.parseDouble(request.getParameter("money").replaceAll("\\.", "").replaceAll(",", ".")));
                withCondition = true;
            }

            if (withCondition) {
                result = dao.selectSqlWithCondidion(curso).toJSONString();
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
            CursosDAO dao = new CursosDAO();
            Cursos curso = new Cursos();
            curso.setId(Integer.parseInt(request.getParameter("id")));
            dao.deleteSql(curso);
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
