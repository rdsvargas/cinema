package dao;

import java.time.LocalTime;
import java.util.List;
import model.Ingresso;

/**
 *
 * @author Roger
 */
public interface IngressoDao extends DaoMain<Ingresso>{

    /**
     *
     * @param ingresso Object Ingresso
     * @param qtd_vendido Quantidade de ingressos vendidos
     */
    public void salvar(Ingresso ingresso, int qtd_vendido);
    
    public List<Ingresso> listaTableView();
}
