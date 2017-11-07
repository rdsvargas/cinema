/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    @Override
    public void deletar(Sessao sessao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizar(Sessao sessao) {
        try {
            String sql = "UPDATE sessao SET sessao_hora = ?, filme_id = ? WHERE sessao_id = ?";
            conectar(sql);
            comando.setString(1, sessao.getHora().toString());
            comando.setInt(2, sessao.getFilme().getId());
            comando.setInt(3, sessao.getId());

            comando.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar filme no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            this.fecharConexao();
        }

    }

    @Override
    public List<Sessao> listar() {
        List<Sessao> listaSessoes = new ArrayList<>();
        try {
            String sql = "SELECT * FROM sessao";

            conectar(sql);
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                Filme filme = new FilmeNegocio().localizarPorId(resultado.getInt("filme_id"));
                Sala sala = new SalaNegocio().localizarPorId(resultado.getInt("sala_id"));
                LocalTime horario = DateUtil.stringToTime(resultado.getString("sessao_hora"));

                listaSessoes.add(new Sessao(horario, sala, filme));
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
}
