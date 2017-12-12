package dao;

import java.time.LocalTime;
import model.Sessao;

/**
 *
 * @author Roger
 */
public interface SessaoDao extends DaoMain<Sessao>{   

    /**
     *
     * @param horario Horario da Sessao
     * @param sala_codigo Codigo da sala
     * @return Object Sessao
     */
    public Sessao localizarPorHorario(LocalTime horario, String sala_codigo);
    
    public Sessao localizarPorId(int sessao_id);

    /**
     *
     * @param sessao_id Identificador da Sessao
     * @param sala_id Identificador da Sala
     * @return Total de ingressos vendidos
     */
    public int ingressosVendidos(int sessao_id, int sala_id);
}
