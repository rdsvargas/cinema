package dao.db;

import dao.IngressoDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.List;
import model.Ingresso;

/**
 *
 * @author Roger
 */
public class IngressoDaoBd extends DaoBdMain<Ingresso> implements IngressoDao {

    @Override
    public int saldoIngressosPorSessaoeSala(LocalTime horario, int sala_id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void salvar(Ingresso ingresso, int qtd_vendido) {
        try {
            String sql = "INSERT INTO ingresso (ingresso_qtd, sesso_id, sala_id) VALUES (?, ?, ?)";
            conectarObtendoId(sql);
            comando.setInt(1, qtd_vendido);
            comando.setInt(2, ingresso.getSessao().getId());
            comando.setInt(3, ingresso.getSala().getId());
            comando.executeUpdate();
            
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()){
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

    @Override
    public void deletar(Ingresso ingresso) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizar(Ingresso ingresso) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Ingresso> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void salvar(Ingresso dominio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
