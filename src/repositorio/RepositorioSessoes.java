package repositorio;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import model.Sessao;

public class RepositorioSessoes {

    private List<Sessao> sessoes;

    public RepositorioSessoes() {
        this.sessoes = new ArrayList<Sessao>();
    }

    public boolean addSessao(Sessao sessao) {
        return (sessoes.add(sessao));
    }

    public List<Sessao> getListaSessoes() {
        return this.sessoes;
    }

    public boolean sessaoExiste(LocalTime hora) {
        for (Sessao sessao : sessoes) {
            if (sessao.getHora() == hora) {
                return true;
            }
        }
        return false;
    }
    
    public Sessao buscaSessao(LocalTime hora){
        for (Sessao sessao: sessoes){
            if (sessao.getHora() == hora){
                return sessao;
            }
        }
        return null;
    }
}
