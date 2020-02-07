package controle;



import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import aplicacao.Alunos;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static jdk.nashorn.internal.objects.NativeError.printStackTrace;
import persistencia.AlunosDAO;
import persistencia.AbstratoDAO;

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String mensagem)
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
      
        try {
            Alunos aluno = new Alunos();
            aluno.setNome(request.getParameter("nome"));
            aluno.setId(Integer.parseInt(request.getParameter("id")));
            aluno.setCpf(request.getParameter("cpf"));
            aluno.setEmail(request.getParameter("email"));
            aluno.setCelular(request.getParameter("celular"));
            aluno.setLogin(request.getParameter("login"));
            aluno.setSenha(request.getParameter("senha"));
            aluno.setEndereco(request.getParameter("endereco"));
            aluno.setCidade(request.getParameter("cidade"));
            aluno.setBairro(request.getParameter("bairro"));
            aluno.setCep(request.getParameter("cep"));
            aluno.setComentario(request.getParameter("comentario"));
            aluno.setAprovado(request.getParameter("aprovado"));

            AlunosDAO dao = new AlunosDAO();

            if (dao.insertSql(aluno)) {
                mensagem = "Aluno inscrito com sucesso!";
                System.out.println(mensagem);
            } else {
                mensagem = "Aluno ja inscrito";
                System.out.println(mensagem);
            }

            processRequest(request, response, mensagem);

        } catch (Exception ex) {
            mensagem = "Erro nao previsto ao insrever aluno";
            System.out.println("Erro ao gravar usuário: " + printStackTrace(ex));
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
