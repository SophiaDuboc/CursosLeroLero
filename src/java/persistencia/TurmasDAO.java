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
            condicao.put("instrutoresId", Integer.toString(turma.getInstrutoresId()));
        }
        if (turma.getCursosId() != 0) {
            condicao.put("cursosId", Integer.toString(turma.getCursosId()));
        }
        if (turma.getDataInicio() != null) {
            condicao.put("dataInicio", String.valueOf(turma.getDataInicio()));
        }
        if (turma.getDataFinal() != null) {
            condicao.put("dataFinal", String.valueOf(turma.getDataFinal()));
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
            turma.setInstrutoresId(resultSet.getInt("instrutoresId"));
            turma.setCursosId(resultSet.getInt("cursosId"));
            turma.setDataInicio(resultSet.getDate("dataInicio"));
            turma.setDataFinal(resultSet.getDate("dataFinal"));
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
                json.put("instrutoresId", turma.getInstrutoresId());
                json.put("cursosId", turma.getCursosId());
                json.put("dataInicio", turma.getDataInicio());
                json.put("dataFinal", turma.getDataFinal());
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
