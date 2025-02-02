/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import aplicacao.Alunos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 *
 * @author sophi
 */
public class AlunosDAO extends AbstratoDAO {

    public AlunosDAO() throws SQLException {
    }

    @Override
    protected String nomeTabela() throws Exception {
        return "alunos";
    }

    @Override
    protected String setColumsNamesToInsert() throws Exception {
        return "(cpf, nome, email, celular, login, senha, endereco, cidade, bairro, cep, comentario)";
    }

    @Override
    protected String setValuesToInsert() throws Exception {
        return "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected PreparedStatement setPreparedStatementToInsert(PreparedStatement statement, Object object) throws Exception {
        Alunos aluno = (Alunos) object;
        statement.setString(1, aluno.getCpf());
        statement.setString(2, aluno.getNome());
        statement.setString(3, aluno.getEmail());
        statement.setString(4, aluno.getCelular());
        statement.setString(5, aluno.getLogin());
        statement.setString(6, aluno.getSenha());
        statement.setString(7, aluno.getEndereco());
        statement.setString(8, aluno.getCidade());
        statement.setString(9, aluno.getBairro());
        statement.setString(10, aluno.getCep());
        statement.setString(11, aluno.getComentario());
        return statement;
    }

    @Override
    protected String setColumsNamesToUpdate() throws Exception {
        return "cpf = ?, "
                + "nome = ?, "
                + "email = ?, "
                + "celular = ?, "
                + "login = ?, "
                + "senha = ?, "
                + "endereco = ?, "
                + "cidade = ?, "
                + "bairro = ?, "
                + "cep = ?, "
                + "comentario = ?, "
                + "aprovado = ?";
    }

    @Override
    protected PreparedStatement setPreparedStatementToUpdate(PreparedStatement statement, Object object) throws Exception {
        Alunos aluno = (Alunos) object;
        statement.setString(1, aluno.getCpf());
        statement.setString(2, aluno.getNome());
        statement.setString(3, aluno.getEmail());
        statement.setString(4, aluno.getCelular());
        statement.setString(5, aluno.getLogin());
        statement.setString(6, aluno.getSenha());
        statement.setString(7, aluno.getEndereco());
        statement.setString(8, aluno.getCidade());
        statement.setString(9, aluno.getBairro());
        statement.setString(10, aluno.getCep());
        statement.setString(11, aluno.getComentario());
        statement.setString(12, aluno.getAprovado());
        statement.setString(13, Integer.toString(aluno.getId()));

        return statement;
    }

    @Override
    protected HashMap conditionToSelect(Object object) throws Exception {
        HashMap<String, String> condicao = new HashMap();
        Alunos aluno = (Alunos) object;
        if (aluno.getId() != 0) {
            condicao.put("id", Integer.toString(aluno.getId()));
        }
        if (aluno.getLogin() != null) {
            condicao.put("login", aluno.getLogin());
        }
        if (aluno.getNome() != null) {
            condicao.put("nome", aluno.getNome());
        }
        if (aluno.getCpf() != null) {
            condicao.put("cpf", aluno.getCpf());
        }
        if (aluno.getEmail() != null) {
            condicao.put("email", aluno.getEmail());
        }
        if (aluno.getAprovado() != null) {
            condicao.put("aprovado", aluno.getAprovado());
        }
        return condicao;
    }

    @Override
    protected ArrayList<Object> getListResponse(ResultSet resultSet) throws Exception {
        ArrayList<Object> response = new ArrayList<>();
        while (resultSet.next()) {
            Alunos aluno = new Alunos();
            aluno.setId(Integer.parseInt(resultSet.getString("id")));
            aluno.setCpf(resultSet.getString("cpf"));
            aluno.setNome(resultSet.getString("nome"));
            aluno.setEmail(resultSet.getString("email"));
            aluno.setCelular(resultSet.getString("celular"));
            aluno.setLogin(resultSet.getString("login"));
            aluno.setSenha(resultSet.getString("senha"));
            aluno.setEndereco(resultSet.getString("endereco"));
            aluno.setCidade(resultSet.getString("cidade"));
            aluno.setBairro(resultSet.getString("bairro"));
            aluno.setCep(resultSet.getString("cep"));
            aluno.setComentario(resultSet.getString("comentario"));
            aluno.setAprovado(resultSet.getString("aprovado"));
            response.add(aluno);
        }
        return response;
    }

    @Override
    protected PreparedStatement setPreparedStatementToDelete(PreparedStatement statement, Object object) throws Exception {
        Alunos aluno = (Alunos) object;
        statement.setInt(1, aluno.getId());
        return statement;
    }

    @Override
    protected JSONObject convertResultToJson(List<Object> result) throws ParseException {
        JSONObject resposta = new JSONObject();
        try {
            resposta.put("current", 1);
            resposta.put("rowCount", 10);
            resposta.put("total", result.size());
            JSONArray jsonArray = new JSONArray();
            for (Object objeto : result) {
                JSONObject json = new JSONObject();
                Alunos aluno = (Alunos) objeto;
                json.put("id", aluno.getId());
                json.put("cpf", aluno.getCpf());
                json.put("nome", aluno.getNome());
                json.put("email", aluno.getEmail());
                json.put("celular", aluno.getCelular());
                json.put("login", aluno.getLogin());
                json.put("senha", aluno.getSenha());
                json.put("endereco", aluno.getEndereco() != null ? aluno.getEndereco() : "");
                json.put("cidade", aluno.getCidade() != null ? aluno.getCidade() : "");
                json.put("bairro", aluno.getBairro() != null ? aluno.getBairro() : "");
                json.put("cep", aluno.getCep() != null ? aluno.getCep() : "");
                json.put("comentario", aluno.getComentario() != null ? aluno.getComentario() : "");
                json.put("aprovado", aluno.getAprovado());
                jsonArray.add(json);
            }
            resposta.put("rows", jsonArray);
        } catch (Exception ex) {
            System.out.println("Erro no metodo convertResultToString(): " + ex.getMessage());
        }
        return resposta;
    }
}
