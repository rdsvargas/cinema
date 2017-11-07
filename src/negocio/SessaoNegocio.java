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

    public SessaoNegocio() {
        this.sessaoDao = new SessaoDaoBd();
    }

    public void salvar(Sessao sessao) {
        sessaoDao.salvar(sessao);
    }
    
    public void atualizar(Sessao sessao){
        this.sessaoDao.atualizar(sessao);
    }
    
    public Sessao localizarPorHorario(LocalTime horario, String sala_codigo){
        return sessaoDao.localizarPorHorario(horario, sala_codigo);
    }
    
    public List<Sessao> listar(){
        return sessaoDao.listar();
    }
}
