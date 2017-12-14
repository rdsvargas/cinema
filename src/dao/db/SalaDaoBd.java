package dao.db;

import dao.SalaDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Sala;

/**
 *
 * @author Roger
 */
public class SalaDaoBd extends DaoBdMain<Sala> implements SalaDao {

    /**
     *
     * @param sala Object Sala
     */
    @Override
    public void salvar(Sala sala) {
        try {
            String sql = "INSERT INTO sala (sala_codigo, sala_qtd_assentos) VALUES (?, ?)";
            conectarObtendoId(sql);
            comando.setString(1, sala.getCodigo());
            comando.setInt(2, sala.getQtdAssentos());
            comando.executeUpdate();

            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                //seta o id para o objeto
                int sala_id = 0;
                sala_id = resultado.getInt(1);
                sala.setId(sala_id);
            } else {
                System.err.println("Erro de Sistema - Nao gerou o id conforme esperado!");
                throw new BDException("Nao gerou o id conforme esperado!");
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar a sala no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            this.fecharConexao();
        }
    }

    /**
     *
     * @param sala Object Sala
     */
    @Override
    public void deletar(Sala sala) {
        try {
            String sql = "DELETE FROM sala WHERE sala_id = ?";

            conectar(sql);
            comando.setInt(1, sala.getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar sala no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    /**
     *
     * @param sala Object Sala
     */
    @Override
    public void atualizar(Sala sala) {
        try {
            String sql = "UPDATE sala SET sala_codigo = ?, sala_qtd_assentos = ? WHERE sala_id = ?";
            conectar(sql);
            comando.setString(1, sala.getCodigo());
            comando.setInt(2, sala.getQtdAssentos());
            comando.setInt(3, sala.getId());

            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao atualizar a Sala no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            this.fecharConexao();
        }
    }

    /**
     *
     * @return Lista Object Sala
     */
    @Override
    public List<Sala> listar() {
        List<Sala> listaSalas = new ArrayList<>();

        String sql = "SELECT * FROM sala ORDER BY sala_codigo";
        try {
            conectar(sql);
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                listaSalas.add(new Sala(
                        resultado.getInt("sala_id"),
                        resultado.getString("sala_codigo"),
                        resultado.getInt("sala_qtd_assentos")));
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar as salas no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return listaSalas;
    }

    /**
     *
     * @param codigo Identificação da Sala
     * @return Object Sala
     */
    @Override
    public Sala localizarPorCodigo(String codigo, boolean upException) {
        Sala sala = null;
        try {
            String sql = "SELECT * FROM sala WHERE UPPER(sala_codigo) = UPPER(?)";
            conectar(sql);
            comando.setString(1, codigo);
            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                sala = new Sala(resultado.getInt("sala_id"),
                        resultado.getString("sala_codigo"),
                        resultado.getInt("sala_qtd_assentos"));
            } else {
                if (upException) {
                    throw new BDException("Sala não encontrada.");
                }
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar a sala do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return sala;
    }

    /**
     *
     * @param id Identificador da Sala
     * @return Object Sala
     */
    @Override
    public Sala localizarPorId(int id) {
        Sala sala = null;
        try {
            String sql = "SELECT * FROM sala WHERE sala_id = ?";
            conectar(sql);
            comando.setInt(1, id);
            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                sala = new Sala(resultado.getInt("sala_id"),
                        resultado.getString("sala_codigo"),
                        resultado.getInt("sala_qtd_assentos"));
            } else {
                throw new BDException("Sala não encontrada.");
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar a sala do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return sala;
    }
}
