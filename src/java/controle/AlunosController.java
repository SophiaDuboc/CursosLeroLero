package controle;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import aplicacao.Alunos;
import static jdk.nashorn.internal.objects.NativeError.printStackTrace;
import persistencia.AlunosDAO;

@WebServlet(name = "AlunosController", urlPatterns = {"/AlunosController"})
public class AlunosController extends HttpServlet {

    /**
     * Processes requests for HTTP <code>POST</code> methods.
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String result = "";
        boolean isSuccess;
        try {
            AlunosDAO dao = new AlunosDAO();
            Alunos aluno = new Alunos();
            aluno.setNome(request.getParameter("nome_completo"));
            aluno.setEmail(request.getParameter("your_email"));
            aluno.setLogin(request.getParameter("username"));
            aluno.setSenha((String) request.getAttribute("password"));
            aluno.setEndereco(request.getParameter("endereco"));
            aluno.setCidade(request.getParameter("cidade"));
            aluno.setBairro(request.getParameter("bairro"));

            if (request.getParameter("cep") != null) {
                aluno.setCep(request.getParameter("cep").replaceAll("\\D+", ""));
            } else {
                aluno.setCep(request.getParameter("cep"));
            }
            if (request.getParameter("celular") != null) {
                aluno.setCelular(request.getParameter("celular").replaceAll("\\D+", ""));
            }
            if (request.getParameter("cpf") != null) {
                aluno.setCpf(request.getParameter("cpf").replaceAll("\\D+", ""));
            } else {
                aluno.setCpf(request.getParameter("cpf"));
            }
            if (request.getParameter("aprovado") != null) {
                aluno.setAprovado(request.getParameter("aprovado"));
            }
            if (request.getParameter("id") == null) {
                System.out.println("A requisicao eh para um insert");
                result = "Inserido ";
                isSuccess = dao.insertSql(aluno);
            } else {
                System.out.println("A requisicao eh para um update");
                result = "Atualizado ";
                aluno.setId(Integer.parseInt(request.getParameter("id")));
                isSuccess = dao.updateSql(aluno);
            }
            if (isSuccess) {
                result += " com sucesso";
                System.out.println(result);
            } else {
                result += " com erro";
                System.out.println(result);
            }
            processRequest(request, response, result);
        } catch (Exception ex) {
            result = "Erro nao previsto ao insrever aluno";
            System.out.println("Erro ao gravar usuário: " + printStackTrace(ex));
            processRequest(request, response, result);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String result;
        try {
            AlunosDAO dao = new AlunosDAO();
            Alunos aluno = new Alunos();

            boolean withCondition = false;
            if (request.getParameter("login") != null) {
                aluno.setLogin(request.getParameter("login"));
                withCondition = true;
            }
            if (request.getParameter("senha") != null) {
                aluno.setSenha(request.getParameter("senha"));
                withCondition = true;
            }
            if (request.getParameter("nome") != null) {
                aluno.setNome(request.getParameter("nome"));
                withCondition = true;
            }
            if (request.getParameter("id") != null) {
                aluno.setId(Integer.parseInt(request.getParameter("id")));
                withCondition = true;
            }
            if (request.getParameter("cpf") != null) {
                aluno.setCpf(request.getParameter("cpf"));
                withCondition = true;
            }
            if (withCondition) {
                result = dao.selectSqlWithCondidion(aluno).toJSONString();
            } else {
                result = dao.selectAllSql().toJSONString();
            }
            String mensagem = "Select foi um sucesso";
            System.out.println(mensagem);
            processRequest(request, response, result);
        } catch (Exception ex) {
            result = "Erro ao listar alunos";
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
            AlunosDAO dao = new AlunosDAO();
            Alunos aluno = new Alunos();
            aluno.setId(Integer.parseInt(request.getParameter("id")));
            dao.deleteSql(aluno);
            result = " com sucesso";
            System.out.println(result);
            processRequest(request, response, result);
        } catch (Exception ex) {
            result = " com erro";
            System.err.println(result + ": " + ex.getMessage());
            processRequest(request, response, result);
        }
    }

}
