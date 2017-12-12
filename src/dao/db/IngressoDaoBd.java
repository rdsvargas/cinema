package dao.db;

import dao.IngressoDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import model.Filme;
import model.Ingresso;
import model.Sala;
import model.Sessao;
import negocio.SessaoNegocio;
import util.DateUtil;

/**
 *
 * @author Roger
 */
public class IngressoDaoBd extends DaoBdMain<Ingresso> implements IngressoDao {

    /**
     *
     * @param horario Horário da Sessao
     * @param sala_id Identificador da Sala
     * @return Saldo de Ingressos da Sessao
     */
    @Override
    public int saldoIngressosPorSessaoeSala(LocalTime horario, int sala_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param ingresso Object Ingresso
     * @param qtd_vendido Quandidade de Ingressos vendidos
     */
    @Override
    public void salvar(Ingresso ingresso, int qtd_vendido) {
        try {
            String sql = "INSERT INTO ingresso (ingresso_qtd, sessao_id, sala_id) VALUES (?, ?, ?)";
            conectarObtendoId(sql);
            comando.setInt(1, qtd_vendido);
            comando.setInt(2, ingresso.getSessao().getId());
            comando.setInt(3, ingresso.getSessao().getSala().getId());
            comando.executeUpdate();

            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                int ingresso_id = resultado.getInt(1);
                ingresso.setId(ingresso_id);
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
     * @param ingresso Object Ingresso
     */
    @Override
    public void deletar(Ingresso ingresso) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param ingresso Object Ingresso
     */
    @Override
    public void atualizar(Ingresso ingresso) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @return Lista Object Ingresso
     */
    @Override
    public List<Ingresso> listar() {
        List<Ingresso> listaIngressos = new ArrayList<>();
        try {
            String sql = "SELECT sessao.*,"
                    + "          sala.*,"
                    + "          filme.*,"
                    + "          ing.ingressos_vendidos,"
                    + "          CASE"
                    + "            WHEN ing.ingressos_vendidos is null then sala.sala_qtd_assentos"
                    + "            ELSE"
                    + "              (sala.sala_qtd_assentos - ing.ingressos_vendidos)"
                    + "          END saldo_ingressos"
                    + "     FROM sessao"
                    + "    INNER JOIN sala"
                    + "       ON sala.sala_id = sessao.sala_id"
                    + "    INNER JOIN filme"
                    + "       ON filme.filme_id = sessao.filme_id"
                    + "     LEFT JOIN (SELECT SUM(ingresso_qtd) ingressos_vendidos, sessao_id"
                    + "                  FROM ingresso"
                    + "                 GROUP BY sessao_id) ing on ing.sessao_id = sessao.sessao_id";
            conectar(sql);
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                Sala sala = new Sala(resultado.getInt("sala_id"),
                        resultado.getString("sala_codigo"),
                        resultado.getInt("sala_qtd_assentos"));
                
                Filme filme = new Filme(resultado.getInt("filme_id"),
                        resultado.getString("filme_nome"),
                        resultado.getString("filme_genero"),
                        resultado.getString("filme_sinopsia"));
                
                Sessao sessao = new Sessao(resultado.getInt("sessao_id"),
                        DateUtil.stringToTime(resultado.getString("sessao_hora")),
                        sala,
                        filme,
                        resultado.getInt("ingressos_vendidos"));
                listaIngressos.add(new Ingresso(sessao, resultado.getInt("ingressos_vendidos")));
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema consulta ingressos no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            this.fecharConexao();
        }
        return listaIngressos;
    }

    @Override
    public List<Ingresso> listaTableView(){
        List<Ingresso> listaIngressos = new ArrayList<>();
        try {
            String sql = "SELECT * FROM ingresso";
            conectar(sql);
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                Sessao sessao = new SessaoNegocio().localizarPorId(resultado.getInt("sessao_id"));
                listaIngressos.add(new Ingresso(resultado.getInt("ingresso_id"), resultado.getInt("ingresso_qtd"), sessao));
            }
            
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema consulta ingressos no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            this.fecharConexao();
        }
        return listaIngressos;
    }
    
    /**
     *
     * @param dominio
     */
    @Override
    public void salvar(Ingresso dominio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
