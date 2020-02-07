/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import aplicacao.Administrador;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author sophi
 */
public class AdministradorDAO extends AbstratoDAO {

    public AdministradorDAO() throws SQLException {
    }

    @Override
    protected String nomeTabela() throws Exception {
        return "administrador";
    }

    @Override
    protected String setColumsNamesToInsert() throws Exception {
        return "(nome, login, senha)";
    }

    @Override
    protected String setValuesToInsert() throws Exception {
        return "(?, ?, ?)";
    }

    @Override
    protected PreparedStatement setPreparedStatementToInsert(PreparedStatement statement, Object object) throws Exception {
        Administrador adm = (Administrador) object;
        statement.setString(1, adm.getNome());
        statement.setString(2, adm.getLogin());
        statement.setString(3, adm.getSenha());
        return statement;
    }

    @Override
    protected String setColumsNamesToUpdate() throws Exception {
        return "nome = ?, "
                + "login = ?, "
                + "senha = ?";
    }

    @Override
    protected PreparedStatement setPreparedStatementToUpdate(PreparedStatement statement, Object object) throws Exception {
        Administrador adm = (Administrador) object;
        statement.setString(1, adm.getNome());
        statement.setString(2, adm.getLogin());
        statement.setString(3, adm.getSenha());
        statement.setInt(4, adm.getId());
        return statement;
    }

    @Override
    protected HashMap<String, String> conditionToSelect(Object object) {
        HashMap<String, String> condicao = new HashMap();
        Administrador adm = (Administrador) object;
        if (adm.getId() != 0) {
            condicao.put("chave", "id");
            condicao.put("valor", Integer.toString(adm.getId()));
        } else if (adm.getLogin() != null) {
            condicao.put("chave", "login");
            condicao.put("valor", adm.getLogin());
        } else if (adm.getSenha() != null) {
            condicao.put("chave", "senha");
            condicao.put("valor", adm.getSenha());
        } else if (adm.getNome() != null) {
            condicao.put("chave", ("nome"));
            condicao.put("senha", adm.getNome());
        }
        return condicao;
    }

    @Override
    protected PreparedStatement setPreparedStatementToSelect(PreparedStatement statement, HashMap object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected ArrayList<Object> getListResponse(ResultSet resultSet) throws Exception {
        ArrayList<Object> response = new ArrayList<>();
        Administrador adm = new Administrador();
        while (resultSet.next()) {
            adm.setId(resultSet.getInt("id"));
            adm.setLogin(resultSet.getString("login"));
            adm.setSenha(resultSet.getString("senha"));
            adm.setNome(resultSet.getString("nome"));
            response.add(adm);
        }
        return response;
    }

    @Override
    protected PreparedStatement setPreparedStatementToDelete(PreparedStatement statement, Object object) throws Exception {
        Administrador adm = (Administrador) object;
        statement.setInt(1, adm.getId());
        return statement;
    }

}
