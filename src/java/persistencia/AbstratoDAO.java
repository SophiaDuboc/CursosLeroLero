/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 *
 * @author sophi
 */
public abstract class AbstratoDAO {

    protected Connection conexao;

    public AbstratoDAO() throws SQLException {
        conexao = Conexao.criaConexao();
    }

    protected abstract String nomeTabela() throws Exception;

    protected abstract String setColumsNamesToInsert() throws Exception;

    protected abstract String setColumsNamesToUpdate() throws Exception;

    protected abstract String setValuesToInsert() throws Exception;

    protected abstract PreparedStatement setPreparedStatementToInsert(PreparedStatement statement, Object object) throws Exception;

    protected abstract PreparedStatement setPreparedStatementToUpdate(PreparedStatement statement, Object object) throws Exception;

    protected abstract PreparedStatement setPreparedStatementToDelete(PreparedStatement statement, Object object) throws Exception;

    protected abstract HashMap conditionToSelect(Object object) throws Exception;

    protected abstract ArrayList<Object> getListResponse(ResultSet result) throws Exception;

    protected abstract JSONObject convertResultToJson(List<Object> result) throws ParseException;

    protected PreparedStatement setPreparedStatementToSelect(PreparedStatement statement, HashMap<Object, Object> object) throws Exception {
        int index = 1;
        for (Map.Entry<Object, Object> entry : object.entrySet()) {
            statement.setString(index, entry.getValue().toString());
            index++;
        }
        return statement;
    }

    public boolean insertSql(Object object) {
        String query = "";
        try {
            query = "INSERT INTO " + nomeTabela() + " " + setColumsNamesToInsert() + " VALUES" + " " + setValuesToInsert();
            PreparedStatement ps = conexao.prepareStatement(query);
            ps = setPreparedStatementToInsert(ps, object);
            ps.execute();
        } catch (Exception ex) {
            System.out.println("Erro ao rodar a query\n" + query + "\n no metodo insertSql(): " + ex.getMessage());
            return false;
        }
        return true;
    }

    public boolean updateSql(Object object) {
        String query = "";
        try {
            query = "UPDATE " + nomeTabela() + " SET " + setColumsNamesToUpdate() + " WHERE id = ?";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps = setPreparedStatementToUpdate(ps, object);
            ps.execute();
        } catch (Exception ex) {
            System.out.println("Erro ao rodar a query\n" + query + "\n no metodo updateSql(): " + ex.getMessage());
            return false;
        }
        return true;
    }

    public JSONObject selectSqlWithCondidion(Object object) {
        ResultSet resultSet;
        String query = "";
        try {
            HashMap<Object, Object> condition = conditionToSelect(object);
            query = "SELECT * FROM " + nomeTabela() + " WHERE " + getQueryConditionToSelect(condition) + " ORDER BY id";
            PreparedStatement ps = conexao.prepareStatement(query);
            ps = setPreparedStatementToSelect(ps, condition);
            resultSet = ps.executeQuery();
            List<Object> result = getListResponse(resultSet);
            return convertResultToJson(result);
        } catch (Exception ex) {
            System.out.println("Erro ao rodar a query\n" + query + "\n no metodo selectSqlWithCondidion(): " + ex.getMessage());
            return null;
        }
    }

    private String getQueryConditionToSelect(HashMap<Object, Object> condition) throws Exception {
        String queryCondition = "";
        int aux = 1;
        for (Map.Entry<Object, Object> entry : condition.entrySet()) {
            if (!(aux == 1)) {
                queryCondition += " AND ";
            }
            queryCondition += entry.getKey() + " = " + "?";
            aux++;
        }
        return queryCondition;
    }

    public JSONObject selectAllSql() {
        ResultSet resultSet;
        String query = "";
        try {
            query = "SELECT * FROM " + nomeTabela() + " ORDER BY id desc";
            PreparedStatement ps = conexao.prepareStatement(query);
            resultSet = ps.executeQuery();
            List<Object> result = getListResponse(resultSet);
            return convertResultToJson(result);
        } catch (Exception ex) {
            System.out.println("Erro ao rodar a query\n" + query + "\n no metodo selectSql(): " + ex.getMessage());
            return null;
        }
    }

    public boolean deleteSql(Object object) {
        String query = "";
        try {
            query = "DELETE FROM " + nomeTabela() + " WHERE id = ?";
            PreparedStatement ps = conexao.prepareCall(query);
            ps = setPreparedStatementToDelete(ps, object);
            ps.execute();
            return true;
        } catch (Exception ex) {
            System.out.println("Erro ao rodar a query\n" + query + "\n no metodo deleteSql(): " + ex.getMessage());
            return false;
        }
    }

}
