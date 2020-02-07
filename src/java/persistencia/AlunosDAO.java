/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import aplicacao.Alunos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author sophi
 */
public class AlunosDAO extends AbstratoDAO {

    public AlunosDAO() throws SQLException {
    }

    @Override
    protected String nomeTabela() throws Exception {
        return "alunos";
    }

    @Override
    protected String setColumsNamesToInsert() throws Exception {
        return "(cpf, nome, email, celular, login, senha, endereco, cidade, bairro, cep, comentario, aprovado)";
    }

    @Override
    protected String setValuesToInsert() throws Exception {
        return "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected PreparedStatement setPreparedStatementToInsert(PreparedStatement statement, Object object) throws Exception {
        Alunos aluno = (Alunos) object;
        statement.setString(1, aluno.getCpf());
        statement.setString(2, aluno.getNome());
        statement.setString(3, aluno.getEmail());
        statement.setString(4, aluno.getCelular());
        statement.setString(5, aluno.getLogin());
        statement.setString(6, aluno.getSenha());
        statement.setString(7, aluno.getEndereco());
        statement.setString(8, aluno.getCidade());
        statement.setString(9, aluno.getBairro());
        statement.setString(10, aluno.getCep());
        statement.setString(11, aluno.getComentario());
        statement.setString(12, aluno.getAprovado());
        return statement;
    }

    @Override
    protected String setColumsNamesToUpdate() throws Exception {
        return "cpf = ?, "
                + "nome = ?, "
                + "email = ?, "
                + "celular = ?, "
                + "login = ?, "
                + "senha = ?, "
                + "endereco = ?, "
                + "cidade = ?, "
                + "bairro = ?, "
                + "cep = ?, "
                + "comentario = ?, "
                + "aprovado = ?";
    }

    @Override
    protected PreparedStatement setPreparedStatementToUpdate(PreparedStatement statement, Object object) throws Exception {
        Alunos aluno = (Alunos) object;
        statement.setString(1, aluno.getCpf());
        statement.setString(2, aluno.getNome());
        statement.setString(3, aluno.getEmail());
        statement.setString(4, aluno.getCelular());
        statement.setString(5, aluno.getLogin());
        statement.setString(6, aluno.getSenha());
        statement.setString(7, aluno.getEndereco());
        statement.setString(8, aluno.getCidade());
        statement.setString(9, aluno.getBairro());
        statement.setString(10, aluno.getCep());
        statement.setString(11, aluno.getComentario());
        statement.setString(12, aluno.getAprovado());
        statement.setString(13, Integer.toString(aluno.getId()));

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
