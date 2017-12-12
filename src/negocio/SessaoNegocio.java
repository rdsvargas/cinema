package negocio;

import dao.SessaoDao;
import dao.db.SessaoDaoBd;
import java.time.LocalTime;
import java.util.List;
import model.Sessao;

/**
 *
 * @author Roger
 */
public class SessaoNegocio {

    private SessaoDao sessaoDao;

    /**
     *
     */
    public SessaoNegocio() {
        this.sessaoDao = new SessaoDaoBd();
    }

    /**
     *
     * @param sessao
     */
    public void salvar(Sessao sessao) {
        sessaoDao.salvar(sessao);
    }
    
    /**
     *
     * @param sessao
     */
    public void atualizar(Sessao sessao){
        this.sessaoDao.atualizar(sessao);
    }

    public void deletar(Sessao sessao){
        this.sessaoDao.deletar(sessao);
    }
    
    /**
     *
     * @param horario
     * @param sala_codigo
     * @return
     */
    public Sessao localizarPorHorario(LocalTime horario, String sala_codigo){
        return sessaoDao.localizarPorHorario(horario, sala_codigo);
    }
    
    public Sessao localizarPorId(int sessao_id){
        return sessaoDao.localizarPorId(sessao_id);
    }
    
    /**
     *
     * @return
     */
    public List<Sessao> listar(){
        return sessaoDao.listar();
    }
    
    /**
     *
     * @param sessao_id
     * @param sala_id
     * @return
     */
    public int ingressosVendidos(int sessao_id, int sala_id){
        return this.sessaoDao.ingressosVendidos(sessao_id, sala_id);
    }
}
