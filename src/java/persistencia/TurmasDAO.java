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
