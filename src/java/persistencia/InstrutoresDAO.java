/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import aplicacao.Instrutores;
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
public class InstrutoresDAO extends AbstratoDAO {

    public InstrutoresDAO() throws SQLException {
    }

    @Override
    protected String nomeTabela() throws Exception {
        return "instrutores";
    }

    @Override
    protected String setColumsNamesToInsert() throws Exception {
        return "(nome, email, valor_hora, login, senha, experiencia)";
    }

    @Override
    protected String setValuesToInsert() throws Exception {
        return "(?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected PreparedStatement setPreparedStatementToInsert(PreparedStatement statement, Object object) throws Exception {
        Instrutores instrutor = (Instrutores) object;
        statement.setString(1, instrutor.getNome());
        statement.setString(2, instrutor.getEmail());
        statement.setInt(3, instrutor.getValorHora());
        statement.setString(4, instrutor.getLogin());
        statement.setString(5, instrutor.getSenha());
        statement.setString(6, instrutor.getExperiencia());
        return statement;
    }

    @Override
    protected String setColumsNamesToUpdate() throws Exception {
        return "nome = ?,"
                + "email = ?,"
                + "valor_hora = ?,"
                + "login = ?,"
                + "experiencia = ?";
    }

    @Override
    protected PreparedStatement setPreparedStatementToUpdate(PreparedStatement statement, Object object) throws Exception {
        Instrutores instrutor = (Instrutores) object;
        statement.setString(1, instrutor.getNome());
        statement.setString(2, instrutor.getEmail());
        statement.setInt(3, instrutor.getValorHora());
        statement.setString(4, instrutor.getLogin());
        statement.setString(5, instrutor.getExperiencia());
        statement.setInt(6, instrutor.getId());

        return statement;
    }

    @Override
    protected HashMap conditionToSelect(Object object) throws Exception {
        HashMap<String, String> condicao = new HashMap();
        Instrutores instrutor = (Instrutores) object;
        if (instrutor.getId() != 0) {
            condicao.put("id", Integer.toString(instrutor.getId()));
        }
        if (instrutor.getNome() != null) {
            condicao.put("nome", instrutor.getNome());
        }
        if (instrutor.getEmail() != null) {
            condicao.put("email", instrutor.getEmail());
        }
        if (instrutor.getValorHora() != 0) {
            condicao.put("valorHora", Integer.toString(instrutor.getValorHora()));
        }
        if (instrutor.getLogin() != null) {
            condicao.put("login", instrutor.getLogin());
        }
        if (instrutor.getSenha() != null) {
            condicao.put("senha", instrutor.getSenha());
        }
        if (instrutor.getExperiencia() != null) {
            condicao.put("experiencia", instrutor.getExperiencia());
        }
        return condicao;
    }

    @Override
    protected ArrayList<Object> getListResponse(ResultSet resultSet) throws Exception {
        ArrayList<Object> response = new ArrayList<>();
        while (resultSet.next()) {
            Instrutores instrutor = new Instrutores();
            instrutor.setId(resultSet.getInt("id"));
            instrutor.setNome(resultSet.getString("nome"));
            instrutor.setEmail(resultSet.getString("email"));
            instrutor.setValorHora(resultSet.getInt("valor_hora"));
            instrutor.setLogin(resultSet.getString("login"));
            instrutor.setSenha(resultSet.getString("senha"));
            instrutor.setExperiencia(resultSet.getString("experiencia"));
            response.add(instrutor);
        }
        return response;
    }

    @Override
    protected PreparedStatement setPreparedStatementToDelete(PreparedStatement statement, Object object) throws Exception {
        Instrutores instrutor = (Instrutores) object;
        statement.setInt(1, instrutor.getId());
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
                Instrutores instrutor = (Instrutores) objeto;
                json.put("id", instrutor.getId());
                json.put("nome", instrutor.getNome());
                json.put("email", instrutor.getEmail());
                json.put("valor_hora", instrutor.getValorHora());
                json.put("login", instrutor.getLogin());
                json.put("senha", instrutor.getSenha());
                json.put("experiencia", instrutor.getExperiencia());
                jsonArray.add(json);
            }
            resposta.put("rows", jsonArray);
        } catch (Exception ex) {
            System.out.println("Erro no metodo convertResultToString(): " + ex.getMessage());
        }
        return resposta;
    }

}
