package repositorio;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import model.Ingresso;

public class RepositorioIngressos {

    private List<Ingresso> ingressos;

    public RepositorioIngressos() {
        this.ingressos = new ArrayList<Ingresso>();
    }
    public boolean addIngresso(Ingresso ingresso) {
        return (ingressos.add(ingresso));
    }

    public List<Ingresso> getListaIngressos() {
        return this.ingressos;
    }

    public boolean venderIngresso(LocalTime hora) {
        boolean result = false;
        for (Ingresso ingresso : ingressos) {
            if (ingresso.getSessao().getHora() == hora){
                if (ingresso.getIngressosVendidos() < ingresso.getSessao().getSala().getQtdAssentos()){
                    ingresso.incrementaIngresso();
                    result = true;
                } else {
                    result = false;
                }
            }
        }
        return result;
    }

}
