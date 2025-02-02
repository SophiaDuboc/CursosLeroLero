/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import aplicacao.Cursos;
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
public class CursosDAO extends AbstratoDAO {

    public CursosDAO() throws SQLException {
    }

    @Override
    protected String nomeTabela() throws Exception {
        return "cursos";
    }

    @Override
    protected String setColumsNamesToInsert() throws Exception {
        return "(nome, requisito, ementa, carga_horaria, preco)";
    }

    @Override
    protected String setValuesToInsert() throws Exception {
        return "(?, ?, ?, ?, ?)";
    }

    @Override
    protected PreparedStatement setPreparedStatementToInsert(PreparedStatement statement, Object object) throws Exception {
        Cursos cursos = (Cursos) object;
        statement.setString(1, cursos.getNome());
        statement.setString(2, cursos.getRequisito());
        statement.setString(3, cursos.getEmenta());
        statement.setShort(4, cursos.getCargaHoraria());
        statement.setDouble(5, cursos.getPreco());
        return statement;
    }

    @Override
    protected String setColumsNamesToUpdate() throws Exception {
        return "nome = ?, "
                + "requisito = ?, "
                + "ementa = ?, "
                + "carga_horaria = ?, "
                + "preco = ?";
    }

    @Override
    protected PreparedStatement setPreparedStatementToUpdate(PreparedStatement statement, Object object) throws Exception {
        Cursos cursos = (Cursos) object;
        statement.setString(1, cursos.getNome());
        statement.setString(2, cursos.getRequisito());
        statement.setString(3, cursos.getEmenta());
        statement.setShort(4, cursos.getCargaHoraria());
        statement.setDouble(5, cursos.getPreco());
        statement.setInt(6, cursos.getId());
        return statement;
    }

    @Override
    protected HashMap conditionToSelect(Object object) {
        HashMap<String, String> condicao = new HashMap();
        Cursos curso = (Cursos) object;
        if (curso.getId() != 0) {
            condicao.put("id", Integer.toString(curso.getId()));
        }
        if (curso.getNome() != null) {
            condicao.put("nome", curso.getNome());
        }
        if (curso.getRequisito() != null) {
            condicao.put("requisito", curso.getRequisito());
        }
        if (curso.getEmenta() != null) {
            condicao.put("ementa", curso.getEmenta());
        }
        if (curso.getCargaHoraria() != 0) {
            condicao.put("carga_horaria", Integer.toString(curso.getCargaHoraria()));
        }
        if (curso.getPreco() != 0) {
            condicao.put("preco", String.valueOf(curso.getPreco()));
        }
        return condicao;
    }

    @Override
    protected ArrayList<Object> getListResponse(ResultSet resultSet) throws Exception {
        ArrayList<Object> response = new ArrayList<>();
        while (resultSet.next()) {
            Cursos curso = new Cursos();
            curso.setId(resultSet.getInt("id"));
            curso.setNome(resultSet.getString("nome"));
            curso.setRequisito(resultSet.getString("requisito"));
            curso.setEmenta(resultSet.getString("ementa"));
            curso.setCargaHoraria(resultSet.getShort("carga_horaria"));
            curso.setPreco(resultSet.getDouble("preco"));
            response.add(curso);
        }
        return response;
    }

    @Override
    protected PreparedStatement setPreparedStatementToDelete(PreparedStatement statement, Object object) throws Exception {
        Cursos curso = (Cursos) object;
        statement.setInt(1, curso.getId());
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
                Cursos curso = (Cursos) objeto;
                json.put("id", curso.getId());
                json.put("nome", curso.getNome());
                json.put("requisito", (curso.getRequisito() != null ? curso.getRequisito() : ""));
                json.put("ementa", (curso.getEmenta() != null ? curso.getEmenta() : ""));
                json.put("carga_horaria", curso.getCargaHoraria());
                json.put("preco", curso.getPreco());
                jsonArray.add(json);
            }
            resposta.put("rows", jsonArray);
        } catch (Exception ex) {
            System.out.println("Erro no metodo convertResultToString(): " + ex.getMessage());
        }
        return resposta;
    }

}
