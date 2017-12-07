package dao.db;

import dao.FilmeDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Filme;

/**
 * 
 * @author Roger
 */
public class FilmeDaoBd extends DaoBdMain<Filme> implements FilmeDao {

    /**
     *
     * @param filme Object Filme
     */
    @Override
    public void salvar(Filme filme) {
        try {
            String sql = "INSERT INTO filme (filme_nome, filme_genero, filme_sinopsia) VALUES (?,?,?)";
            conectarObtendoId(sql);
            comando.setString(1, filme.getNome());
            comando.setString(2, filme.getGenero());
            comando.setString(3, filme.getSinopsia());
            comando.executeUpdate();

            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                //seta o id para o objeto
                int filme_id = 0;
                filme_id = resultado.getInt(1);
                filme.setId(filme_id);
            } else {
                System.err.println("Erro de Sistema - Nao gerou o id conforme esperado!");
                throw new BDException("Nao gerou o id conforme esperado!");
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar filme no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            this.fecharConexao();
        }
    }

    /**
     *
     * @param filme Object Filme
     */
    @Override
    public void deletar(Filme filme) {
        try {
            String sql = "DELETE FROM filme WHERE filme_id = ?";

            conectar(sql);
            comando.setInt(1, filme.getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar filme no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    /**
     *
     * @param filme Object Filme
     */
    @Override
    public void atualizar(Filme filme) {
        try {
            String sql = "UPDATE filme set filme_nome = ?, filme_genero = ?, filme_sinopsia = ? where filme_id = ?";
            conectar(sql);
            comando.setString(1, filme.getNome());
            comando.setString(2, filme.getGenero());
            comando.setString(3, filme.getSinopsia());
            comando.setInt(4, filme.getId());

            comando.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao atualizar paciente no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            this.fecharConexao();
        }
    }

    /**
     *
     * @return Lista Object Filme
     */
    @Override
    public List<Filme> listar() {
        List<Filme> listaFilmes = new ArrayList<>();

        String sql = "SELECT * FROM filme ORDER BY filme_nome";

        try {
            conectar(sql);
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                int id = resultado.getInt("filme_id");
                String nome = resultado.getString("filme_nome");
                String genero = resultado.getString("filme_genero");
                String sinopisa = resultado.getString("filme_sinopsia");

                Filme filme = new Filme(id, nome, genero, sinopisa);
                listaFilmes.add(filme);
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os pacientes do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return listaFilmes;
    }

    /**
     *
     * @param id Identificador do Filme
     * @return Object Filme
     */
    @Override
    public Filme localizarPorId(int id) {
        Filme filme = null;

        try {
            String sql = "SELECT * FROM filme WHERE filme_id = ?";
            conectar(sql);
            comando.setInt(1, id);
            ResultSet resultado = comando.executeQuery();
            if (resultado.next()) {
                String nome = resultado.getString("filme_nome");
                String genero = resultado.getString("filme_genero");
                String sinopisa = resultado.getString("filme_sinopsia");

                filme = new Filme(id, nome, genero, sinopisa);
            } else {
                throw new BDException("Filme não encontrada.");
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os pacientes do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return filme;
    }
}
