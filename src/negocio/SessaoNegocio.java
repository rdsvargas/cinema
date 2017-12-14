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
     * @throws java.lang.Exception
     */
    public void salvar(Sessao sessao) throws Exception {
        if (sessaoDao.localizarHoraBySala(sessao).isEmpty()) {
            sessaoDao.salvar(sessao);
        } else {
            throw new Exception("Sessão já cadastrada");
        }
    }

    /**
     *
     * @param sessao
     * @throws java.lang.Exception
     */
    public void atualizar(Sessao sessao) throws Exception {
        List<Sessao> listaSessoes = sessaoDao.localizarHoraBySala(sessao);
        
        if ((listaSessoes.isEmpty()) || (listaSessoes.size() == 1 && listaSessoes.get(0).getId() == sessao.getId())) {
            this.sessaoDao.atualizar(sessao);
        } else {
            throw new Exception("Sessão já cadastrada");
        }
    }

    public void deletar(Sessao sessao) {
        this.sessaoDao.deletar(sessao);
    }

    /**
     *
     * @param horario
     * @param sala_codigo
     * @return
     */
    public Sessao localizarPorHorario(LocalTime horario, String sala_codigo) {
        return sessaoDao.localizarPorHorario(horario, sala_codigo);
    }

    public Sessao localizarPorId(int sessao_id) {
        return sessaoDao.localizarPorId(sessao_id);
    }

    /**
     *
     * @return
     */
    public List<Sessao> listar() {
        return sessaoDao.listar();
    }

    public List<Sessao> listaSessaoByFilme(int filme_id){
        return this.sessaoDao.listaSessaoByFilme(filme_id);
    }
    
    /**
     *
     * @param sessao_id
     * @param sala_id
     * @return
     */
    public int ingressosVendidos(int sessao_id, int sala_id) {
        return this.sessaoDao.ingressosVendidos(sessao_id, sala_id);
    }
}
