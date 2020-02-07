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
    protected PreparedStatement setPreparedStatementToSelect(PreparedStatement statement, HashMap object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected HashMap conditionToSelect(Object object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected ArrayList<Object> getListResponse(ResultSet result) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected PreparedStatement setPreparedStatementToDelete(PreparedStatement statement, Object object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
