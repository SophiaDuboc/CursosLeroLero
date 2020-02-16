/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import aplicacao.Cursos;
import aplicacao.Instrutores;
import aplicacao.Turmas;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import persistencia.CursosDAO;
import persistencia.InstrutoresDAO;
import persistencia.TurmasDAO;
import java.text.SimpleDateFormat;

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
        boolean fazRequisicao = true;
        boolean isSuccess;
        try {
            TurmasDAO dao = new TurmasDAO();
            Turmas turma = new Turmas();
            Date dataIni = getData(request.getParameter("data_inicio"));
            Date dataFinal = getData(request.getParameter("data_final"));
            turma.setDataInicio(dataIni);
            turma.setDataFinal(dataFinal);
            turma.setCargaHoraria(Short.parseShort(request.getParameter("carga_horaria")));

            Integer idCurso = getIdCurso(request.getParameter("cursos_id"));
            Integer idInstrutor = getIdInstrutor(request.getParameter("instrutores_id"));
            if (idCurso == 0) {
                result = "Não existe este curso";
                fazRequisicao = false;
            } else {
                turma.setCursosId(idCurso);
            }
            if (idInstrutor == 0) {
                result += "\nNão existe este instrutor";
                fazRequisicao = false;
            } else {
                turma.setInstrutoresId(idInstrutor);
            }
            if (fazRequisicao) {
                isSuccess = dao.insertSql(turma);
            } else {
                isSuccess = false;
            }
            if (request.getParameter("id") == null) {
                System.out.println("A requisicao eh para um insert");
            } else {
                System.out.println("A requisicao eh para um update");
                result = "Atualizado ";
                turma.setId(Integer.parseInt(request.getParameter("id")));
                isSuccess = dao.updateSql(turma);
            }
            if (isSuccess) {
                result += "com sucesso";
                System.out.println(result);
            } else {
                System.out.println(result);
            }
            processRequest(request, response, result);
        } catch (Exception ex) {
            result += "com problemas";
            System.out.println(result + " : " + ex.getMessage());
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
        String result;
        try {
            JSONObject resposta = new JSONObject();
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
                resposta = dao.selectSqlWithCondidion(turmas);

            } else {
                resposta = dao.selectAllSql();
            }
            result = getResultCerto(resposta);

            String mensagem = "Select foi um sucesso";
            System.out.println(mensagem);
            processRequest(request, response, result);
        } catch (Exception ex) {
            result = "Erro ao listar turmas";
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
        String result = "Deletado ";
        try {
            TurmasDAO dao = new TurmasDAO();
            Turmas turmas = new Turmas();
            turmas.setId(Integer.parseInt(request.getParameter("id")));
            dao.deleteSql(turmas);
            result += "com sucesso";
            System.out.println(result);
            processRequest(request, response, result);
        } catch (Exception ex) {
            result = "com erro";
            System.err.println(result + ": " + ex.getMessage());
        }
    }

    protected Date getData(String data) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            return new java.sql.Date(formatter.parse(data).getTime());
        } catch (Exception ex) {
            System.err.println("Erro ao parsear data: " + ex.getMessage());
            return null;
        }
    }

    protected Integer getIdCurso(String nomeCurso) throws SQLException {
        JSONObject curso = getCurso(nomeCurso);
        if ((Integer) (curso.get("total")) == 0) {
            return 0;
        }
        return (Integer) ((JSONObject) ((JSONArray) curso.get("rows")).get(0)).get("id");
    }

    protected JSONObject getCurso(String nomeCurso) throws SQLException {
        CursosDAO dao = new CursosDAO();
        Cursos curso = new Cursos();
        curso.setNome(nomeCurso);
        JSONObject a = dao.selectSqlWithCondidion(curso);
        return a;
    }

    protected Integer getIdInstrutor(String nomeInstrutor) throws SQLException {
        JSONObject instrutor = getInstrutor(nomeInstrutor);
        if ((Integer) (instrutor.get("total")) == 0) {
            return 0;
        }
        return (Integer) ((JSONObject) ((JSONArray) instrutor.get("rows")).get(0)).get("id");
    }

    protected JSONObject getInstrutor(String nomeInstrutor) throws SQLException {
        InstrutoresDAO dao = new InstrutoresDAO();
        Instrutores instrutor = new Instrutores();
        instrutor.setNome(nomeInstrutor);
        JSONObject a = dao.selectSqlWithCondidion(instrutor);
        return a;
    }

    protected String getResultCerto(JSONObject resposta) throws SQLException {
        Integer total = (Integer) resposta.get("total");
        for (int i = 0; i < total; i++) {
            JSONObject newRow = (JSONObject) (((JSONArray) resposta.get("rows")).get(i));
            Integer instrutorId = (Integer) ((JSONObject) ((JSONArray) resposta.get("rows")).get(i)).get("instrutores_id");
            Integer cursoId = (Integer) ((JSONObject) ((JSONArray) resposta.get("rows")).get(i)).get("cursos_id");
            newRow.put("cursos_id", getNomeCurso(cursoId));
            newRow.put("instrutores_id", getNomeInstrutor(instrutorId));
        }
        return resposta.toJSONString();
    }

    protected String getNomeCurso(Integer idCurso) throws SQLException {
        Cursos curso = new Cursos();
        CursosDAO dao = new CursosDAO();
        curso.setId(idCurso);
        JSONObject resp = dao.selectSqlWithCondidion(curso);
        return (String) ((JSONObject) ((JSONArray) resp.get("rows")).get(0)).get("nome");
    }

    protected String getNomeInstrutor(Integer idInstrutor) throws SQLException {
        Instrutores instrutor = new Instrutores();
        InstrutoresDAO dao = new InstrutoresDAO();
        instrutor.setId(idInstrutor);
        JSONObject resp = dao.selectSqlWithCondidion(instrutor);
        return (String) ((JSONObject) ((JSONArray) resp.get("rows")).get(0)).get("nome");
    }

}
