/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import aplicacao.Matriculas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;

/**
 *
 * @author sophi
 */
public class MatriculasDAO extends AbstratoDAO {

    public MatriculasDAO() throws SQLException {
    }

    @Override
    protected String nomeTabela() throws Exception {
        return "matriculas";
    }

    @Override
    protected String setColumsNamesToInsert() throws Exception {
        return "(turmas_id, alunos_id, data_matricula, nota)";
    }

    @Override
    protected String setValuesToInsert() throws Exception {
        return "(?, ?, ?, ?)";
    }

    @Override
    protected PreparedStatement setPreparedStatementToInsert(PreparedStatement statement, Object object) throws Exception {
        Matriculas matricula = (Matriculas) object;
        statement.setInt(1, matricula.getTurmaId());
        statement.setInt(2, matricula.getAlunoId());
        statement.setDate(3, matricula.getDate());
        statement.setDouble(4, matricula.getNota());
        return statement;
    }

    @Override
    protected String setColumsNamesToUpdate() throws Exception {
        return "turmas_id = ?,"
                + "alunos_id = ?,"
                + "data_matricula = ?,"
                + "nota = ?,";
    }

    @Override
    protected PreparedStatement setPreparedStatementToUpdate(PreparedStatement statement, Object object) throws Exception {
        Matriculas matricula = (Matriculas) object;
        statement.setInt(1, matricula.getTurmaId());
        statement.setInt(2, matricula.getAlunoId());
        statement.setDate(3, matricula.getDate());
        statement.setDouble(4, matricula.getNota());
        statement.setInt(5, matricula.getId());
        return statement;
    }

    @Override
    protected HashMap conditionToSelect(Object object) throws Exception {
        HashMap<String, String> condicao = new HashMap();
        Matriculas mat = (Matriculas) object;
        if (mat.getId() != 0) {
            condicao.put("id", Integer.toString(mat.getId()));
        }
        if (mat.getTurmaId() != 0) {
            condicao.put("turmasId", Integer.toString(mat.getTurmaId()));
        }
        if (mat.getAlunoId() != 0) {
            condicao.put("alunosId", Integer.toString(mat.getAlunoId()));
        }
        if (mat.getDate() != null) {
            condicao.put("dataMatricula", String.valueOf(mat.getDate()));
        }
        if (mat.getNota() != 0) {
            condicao.put("nota", Double.toString(mat.getNota()));
        }
        return condicao;
    }

    @Override
    protected ArrayList<Object> getListResponse(ResultSet resultSet) throws Exception {
        ArrayList<Object> response = new ArrayList<>();
        while (resultSet.next()) {
            Matriculas mat = new Matriculas();
            mat.setId(resultSet.getInt("id"));
            mat.setTurmaId(resultSet.getInt("turmasId"));
            mat.setAlunoId(resultSet.getInt("alunosId"));
            mat.setDate(resultSet.getDate("dataMatricula"));
            mat.setNota(resultSet.getDouble("nota"));
            response.add(mat);
        }
        return response;
    }

    @Override
    protected PreparedStatement setPreparedStatementToDelete(PreparedStatement statement, Object object) throws Exception {
        Matriculas mat = (Matriculas) object;
        statement.setInt(1, mat.getId());
        return statement;
    }

    @Override
    protected String convertResultToString(List<Object> result) throws ParseException {
        JSONArray jsonArray = new JSONArray();
        try {
            for (Object objeto : result) {
                JSONObject json = new JSONObject();
                Matriculas mat = new Matriculas();
                json.put("id", mat.getId());
                json.put("turmasId", mat.getTurmaId());
                json.put("alunosId", mat.getAlunoId());
                json.put("dataMatricula", mat.getDate());
                json.put("nota", mat.getNota());
            }
        } catch (Exception ex) {
            System.out.println("Erro no metodo convertResultToString(): " + ex.getMessage());
        }
        return jsonArray.toString();
    }

}
