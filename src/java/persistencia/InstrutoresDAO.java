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
                + "senha = ?,"
                + "experiencia = ?,";
    }

    @Override
    protected PreparedStatement setPreparedStatementToUpdate(PreparedStatement statement, Object object) throws Exception {
        Instrutores instrutor = (Instrutores) object;
        statement.setString(1, instrutor.getNome());
        statement.setString(2, instrutor.getEmail());
        statement.setInt(3, instrutor.getValorHora());
        statement.setString(4, instrutor.getLogin());
        statement.setString(5, instrutor.getSenha());
        statement.setString(6, instrutor.getExperiencia());
        statement.setInt(7, instrutor.getId());

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
