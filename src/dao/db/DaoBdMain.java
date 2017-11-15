package dao.db;

import dao.DaoMain;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Roger
 * @param <T>
 */
public abstract class DaoBdMain<T> implements DaoMain<T> {

    /**
     *
     */
    protected Connection conexao;

    /**
     *
     */
    protected PreparedStatement comando;

    /**
     *
     * @param sql Script (SQL)
     * @return Object Connection
     * @throws SQLException
     */
    public Connection conectar(String sql) throws SQLException {
        conexao = ConnectionFactory.getConnection();
        comando = conexao.prepareStatement(sql);
        return conexao;
    }

    /**
     *
     * @param sql Script (SQL)
     * @throws SQLException
     */
    public void conectarObtendoId(String sql) throws SQLException {
        conexao = ConnectionFactory.getConnection();
        comando = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
    }

    /**
     * Fechar conexão
     */
    public void fecharConexao() {
        try {
            if (comando != null) {
                comando.close();
            }
            if (conexao != null) {
                conexao.close();
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Erro ao encerrar a conexao");
            throw new BDException(ex);
        }
    }
}
