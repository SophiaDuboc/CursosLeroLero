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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String mensagem, String resul)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AlunosController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CursosController at " + request.getContextPath() + "</h1>");
            out.println(mensagem);
            out.println("<b>" + mensagem + "</b>");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String mensagem;
        boolean isSuccess;
        try {
            AlunosDAO dao = new AlunosDAO();
            Alunos aluno = new Alunos();
            aluno.setNome(request.getParameter("nome_completo"));
            aluno.setCpf(request.getParameter("cpf"));
            aluno.setEmail(request.getParameter("your_email"));
            aluno.setCelular(request.getParameter("celular"));
            aluno.setLogin(request.getParameter("username"));
            aluno.setSenha(request.getParameter("password"));
            aluno.setEndereco(request.getParameter("endereco"));
            aluno.setCidade(request.getParameter("cidade"));
            aluno.setBairro(request.getParameter("bairro"));
            aluno.setCep(request.getParameter("cep"));
//            aluno.setComentario(request.getParameter("comentario"));
//            aluno.setAprovado(request.getParameter(" aprovado"));

            if (request.getParameter("id") == null) {
                System.out.println("A requisicao eh para um insert");
                isSuccess = dao.insertSql(aluno);
            } else {
                System.out.println("A requisicao eh para um update");
                aluno.setId(Integer.parseInt(request.getParameter("id")));
                isSuccess = dao.updateSql(aluno);
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
            mensagem = "Erro nao previsto ao insrever aluno";
            System.out.println("Erro ao gravar usuário: " + printStackTrace(ex));
            processRequest(request, response, mensagem, "");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mensagem;
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
                aluno.setCep(request.getParameter("cpf"));
                withCondition = true;
            }
            if (withCondition) {
                result = dao.selectSqlWithCondidion(aluno);
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
            AlunosDAO dao = new AlunosDAO();
            Alunos aluno = new Alunos();
            aluno.setId(Integer.parseInt(request.getParameter("id")));
            dao.deleteSql(aluno);
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
    }
}
