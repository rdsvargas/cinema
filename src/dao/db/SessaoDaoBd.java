package dao.db;

import dao.SessaoDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import model.Filme;
import model.Sala;
import model.Sessao;
import negocio.FilmeNegocio;
import negocio.SalaNegocio;
import util.DateUtil;

/**
 *
 * @author Roger
 */
public class SessaoDaoBd extends DaoBdMain<Sessao> implements SessaoDao {

    /**
     *
     * @param sessao Object Sessao
     */
    @Override
    public void salvar(Sessao sessao) {
        try {
            String sql = "INSERT INTO sessao (sessao_hora, filme_id, sala_id) VALUES (?, ?, ?)";
            conectarObtendoId(sql);
            comando.setString(1, sessao.getHora().toString());
            comando.setInt(2, sessao.getFilme().getId());
            comando.setInt(3, sessao.getSala().getId());

            comando.executeUpdate();

            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                //seta o id para o objeto
                int sessao_id = 0;
                sessao_id = resultado.getInt(1);
                sessao.setId(sessao_id);

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
     * @param sessao Object Sessao
     */
    @Override
    public void deletar(Sessao sessao) {
        try {
            String sql = "DELETE FROM sessao WHERE sessao_id = ?";

            conectar(sql);
            comando.setInt(1, sessao.getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar sessão no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    /**
     *
     * @param sessao Object Sessao
     */
    @Override
    public void atualizar(Sessao sessao) {
        try {
            String sql = "UPDATE sessao SET sessao_hora = ?, filme_id = ?, sala_id = ? WHERE sessao_id = ?";
            conectar(sql);
            comando.setString(1, sessao.getHora().toString());
            comando.setInt(2, sessao.getFilme().getId());
            comando.setInt(3, sessao.getSala().getId());
            comando.setInt(4, sessao.getId());

            comando.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar filme no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            this.fecharConexao();
        }

    }

    /**
     *
     * @return Lista Object Sessao
     */
    @Override
    public List<Sessao> listar() {
        List<Sessao> listaSessoes = new ArrayList<>();
        try {
            String sql = "SELECT * FROM sessao ORDER BY sessao_hora";

            conectar(sql);
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                Filme filme = new FilmeNegocio().localizarPorId(resultado.getInt("filme_id"));
                Sala sala = new SalaNegocio().localizarPorId(resultado.getInt("sala_id"));
                LocalTime horario = DateUtil.stringToTime(resultado.getString("sessao_hora"));
                int sessaoId = Integer.parseInt(resultado.getString("sessao_id"));

                listaSessoes.add(new Sessao(sessaoId, horario, sala, filme));
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar a sessão do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return listaSessoes;
    }

    /**
     *
     * @param horario Horario da Sessao
     * @param sala_codigo Identificação da Sala
     * @return
     */
    @Override
    public Sessao localizarPorHorario(LocalTime horario, String sala_codigo) {
        Sessao sessao = null;
        try {
            String sql = "SELECT ss.* FROM sessao ss "
                    + "INNER JOIN sala sl on sl.sala_id = ss.sala_id "
                    + "WHERE ss.sessao_hora = ? AND UPPER(sl.sala_codigo) = UPPER(?)";

            conectar(sql);
            comando.setString(1, horario.toString());
            comando.setString(2, sala_codigo);
            ResultSet resultado = comando.executeQuery();
            if (resultado.next()) {
                Filme filme = new FilmeNegocio().localizarPorId(resultado.getInt("filme_id"));
                Sala sala = new SalaNegocio().localizarPorCodigo(sala_codigo);

                int sessao_id = resultado.getInt("sessao_id");
                int ingressos_vendidos = this.ingressosVendidos(sessao_id, sala.getId());
                sessao = new Sessao(sessao_id, horario, sala, filme, ingressos_vendidos);

            } else {
                throw new BDException("Sessão não encontrada.");
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar a sessão do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return sessao;
    }

    @Override
    public Sessao localizarPorId(int sessao_id) {
        Sessao sessao = null;
        try {
            String sql = "SELECT * FROM sessao WHERE sessao_id = ?";

            conectar(sql);
            comando.setInt(1, sessao_id);
            ResultSet resultado = comando.executeQuery();
            if (resultado.next()) {
                Filme filme = new FilmeNegocio().localizarPorId(resultado.getInt("filme_id"));
                Sala sala = new SalaNegocio().localizarPorId(resultado.getInt("sala_id"));

                sessao = new Sessao(
                        sessao_id,
                        DateUtil.stringToTime(resultado.getString("sessao_hora")),
                        sala,
                        filme);

            } else {
                throw new BDException("Sessão não encontrada.");
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar a sessão do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return sessao;
    }

    /**
     *
     * @param sessao_id Identicador da Sessao
     * @param sala_id Idenficador da Sala
     * @return Quantidade de Ingressos Vendidos
     */
    @Override
    public int ingressosVendidos(int sessao_id, int sala_id) {
        int result = 0;
        try {
            String sql = "SELECT coalesce(SUM(ingresso_qtd), 0) total "
                    + "FROM ingresso WHERE sessao_id = ? AND sala_id  = ?";
            conectar(sql);
            comando.setInt(1, sessao_id);
            comando.setInt(2, sala_id);
            ResultSet resultado = comando.executeQuery();
            if (resultado.next()) {
                result = resultado.getInt("total");
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao acessao quantidade de ingressos do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return result;
    }

    @Override
    public List<Sessao> localizarHoraBySala(Sessao sessao) {
        List<Sessao> listaSessoes = new ArrayList<>();
        try {
            String sql = "SELECT * FROM sessao WHERE sessao_hora = ? and sala_id = ?";

            conectar(sql);
            comando.setString(1, DateUtil.timeToString(sessao.getHora()));
            comando.setInt(2, sessao.getSala().getId());
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                Filme filme = new FilmeNegocio().localizarPorId(resultado.getInt("filme_id"));
                Sala sala = new SalaNegocio().localizarPorId(resultado.getInt("sala_id"));
                LocalTime horario = DateUtil.stringToTime(resultado.getString("sessao_hora"));
                int sessaoId = Integer.parseInt(resultado.getString("sessao_id"));

                listaSessoes.add(new Sessao(sessaoId, horario, sala, filme));
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar a sessão do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return listaSessoes;
    }

    @Override
    public List<Sessao> listaSessaoByFilme(int filme_id) {
        List<Sessao> listaSessoes = new ArrayList<>();
        try {
            String sql = "SELECT sessao.*,"
                    + "          sala.sala_qtd_assentos,"
                    + "          ing.ingressos_vendidos,"
                    + "          CASE"
                    + "            WHEN ing.ingressos_vendidos is null then sala.sala_qtd_assentos"
                    + "            ELSE (sala.sala_qtd_assentos - ing.ingressos_vendidos)"
                    + "          END saldo_ingressos"
                    + "     FROM sessao"
                    + "    INNER JOIN sala"
                    + "       ON sala.sala_id = sessao.sala_id"
                    + "    INNER JOIN filme"
                    + "       ON filme.filme_id = sessao.filme_id"
                    + "     LEFT JOIN (SELECT SUM(ingresso_qtd) ingressos_vendidos, sessao_id"
                    + "                  FROM ingresso"
                    + "                 GROUP BY sessao_id) ing on ing.sessao_id = sessao.sessao_id"
                    + "    WHERE sessao.filme_id = ?";

            conectar(sql);
            comando.setInt(1, filme_id);
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                Filme filme = new FilmeNegocio().localizarPorId(resultado.getInt("filme_id"));
                Sala sala = new SalaNegocio().localizarPorId(resultado.getInt("sala_id"));
                LocalTime horario = DateUtil.stringToTime(resultado.getString("sessao_hora"));
                int sessaoId = Integer.parseInt(resultado.getString("sessao_id"));

                listaSessoes.add(new Sessao(sessaoId, horario, sala, filme, resultado.getInt("ingressos_vendidos")));
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar a sessão do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return listaSessoes;
    }
}
