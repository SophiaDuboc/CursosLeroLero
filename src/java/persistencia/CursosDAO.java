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
                + "preco = ?,";
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
