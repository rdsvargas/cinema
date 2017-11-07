package dao;

import java.time.LocalTime;
import model.Ingresso;

/**
 *
 * @author Roger
 */
public interface IngressoDao extends DaoMain<Ingresso>{
    public int saldoIngressosPorSessaoeSala(LocalTime horario, int sala_id);
    public void salvar(Ingresso ingresso, int qtd_vendido);
}
