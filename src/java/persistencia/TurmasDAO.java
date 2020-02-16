/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import aplicacao.Turmas;
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
public class TurmasDAO extends AbstratoDAO {

    public TurmasDAO() throws SQLException {
    }

    @Override
    protected String nomeTabela() throws Exception {
        return "turmas";
    }

    @Override
    protected String setColumsNamesToInsert() throws Exception {
        return "(instrutores_id, cursos_id, data_inicio, data_final, carga_horaria)";
    }

    @Override
    protected String setValuesToInsert() throws Exception {
        return "(?, ?, ?, ?, ?)";
    }

    @Override
    protected PreparedStatement setPreparedStatementToInsert(PreparedStatement statement, Object object) throws Exception {
        Turmas turma = (Turmas) object;
        statement.setInt(1, turma.getInstrutoresId());
        statement.setInt(2, turma.getCursosId());
        statement.setDate(3, turma.getDataInicio());
        statement.setDate(4, turma.getDataFinal());
        statement.setShort(5, turma.getCargaHoraria());
        return statement;
    }

    @Override
    protected String setColumsNamesToUpdate() throws Exception {
        return "instrutores_id = ?,"
                + "cursos_id = ?,"
                + "data_inicio = ?,"
                + "data_final = ?,"
                + "carga_horaria = ?";
    }

    @Override
    protected PreparedStatement setPreparedStatementToUpdate(PreparedStatement statement, Object object) throws Exception {
        Turmas turma = (Turmas) object;
        statement.setInt(1, turma.getInstrutoresId());
        statement.setInt(2, turma.getCursosId());
        statement.setDate(3, turma.getDataInicio());
        statement.setDate(4, turma.getDataFinal());
        statement.setShort(5, turma.getCargaHoraria());
        statement.setInt(6, turma.getId());
        return statement;
    }

    @Override
    protected HashMap conditionToSelect(Object object) throws Exception {
        HashMap<String, String> condicao = new HashMap();
        Turmas turma = new Turmas();
        if (turma.getId() != 0) {
            condicao.put("id", Integer.toString(turma.getId()));
        }
        if (turma.getInstrutoresId() != 0) {
            condicao.put("instrutores_id", Integer.toString(turma.getInstrutoresId()));
        }
        if (turma.getCursosId() != 0) {
            condicao.put("cursos_id", Integer.toString(turma.getCursosId()));
        }
        if (turma.getDataInicio() != null) {
            condicao.put("data_inicio", String.valueOf(turma.getDataInicio()));
        }
        if (turma.getDataFinal() != null) {
            condicao.put("data_final", String.valueOf(turma.getDataFinal()));
        }
        if (turma.getCargaHoraria() != 0) {
            condicao.put("carga_horaria", Integer.toString(turma.getCargaHoraria()));
        }
        return condicao;
    }

    @Override
    protected ArrayList<Object> getListResponse(ResultSet resultSet) throws Exception {
        ArrayList<Object> response = new ArrayList<>();
        while (resultSet.next()) {
            Turmas turma = new Turmas();
            turma.setId(resultSet.getInt("id"));
            turma.setInstrutoresId(resultSet.getInt("instrutores_id"));
            turma.setCursosId(resultSet.getInt("cursos_id"));
            turma.setDataInicio(resultSet.getDate("data_inicio"));
            turma.setDataFinal(resultSet.getDate("data_final"));
            turma.setCargaHoraria(resultSet.getShort("carga_horaria"));
            response.add(turma);
        }
        return response;
    }

    @Override
    protected PreparedStatement setPreparedStatementToDelete(PreparedStatement statement, Object object) throws Exception {
        Turmas turma = (Turmas) object;
        statement.setInt(1, turma.getId());
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
                Turmas turma = (Turmas) objeto; 
                json.put("id", turma.getId());
                json.put("instrutores_id", turma.getInstrutoresId());
                json.put("cursos_id", turma.getCursosId());
                json.put("data_inicio", (turma.getDataInicio() != null ? turma.getDataInicio().toString() : ""));
                json.put("data_final", (turma.getDataFinal() != null ? turma.getDataFinal().toString() : ""));
                json.put("carga_horaria", turma.getCargaHoraria());
                jsonArray.add(json);
            }
            resposta.put("rows", jsonArray);
        } catch (Exception ex) {
            System.out.println("Erro no metodo convertResultToString(): " + ex.getMessage());
        }
        return resposta;
    }
}
