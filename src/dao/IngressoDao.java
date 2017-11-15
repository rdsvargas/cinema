package dao;

import java.time.LocalTime;
import model.Ingresso;

/**
 *
 * @author Roger
 */
public interface IngressoDao extends DaoMain<Ingresso>{

    /**
     *
     * @param horario Horario da sessao
     * @param sala_id identificador da sala
     * @return Saldo de ingressos disponíveis
     */
    public int saldoIngressosPorSessaoeSala(LocalTime horario, int sala_id);

    /**
     *
     * @param ingresso Classe Ingresso
     * @param qtd_vendido Quantidade de ingressos vendidos
     */
    public void salvar(Ingresso ingresso, int qtd_vendido);
}
