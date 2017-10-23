package dao.db;

import dao.FilmeDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Filme;

/**
 *
 * @author Roger
 */
public class FilmeDAOBD implements FilmeDAO {

    private Connection conexao;
    private PreparedStatement comando;

    public void conectar(String sql) throws SQLException {
        conexao = ConnectionFactory.getConnection();
        comando = conexao.prepareStatement(sql);
    }

    public void fecharConexao() {
        try {
            if (conexao != null) {
                conexao.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(FilmeDAOBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void salvar(Filme filme) {
        try {
            String sql = "insert into pessoa values ((select count(*)+1 from pessoa), ?, ?)";
            this.conectar(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(FilmeDAOBD.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            this.fecharConexao();
        }
    }

    @Override
    public void deletar(Filme filme) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizar(Filme filme) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
