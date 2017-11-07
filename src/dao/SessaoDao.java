package dao;

import java.time.LocalTime;
import model.Sessao;

/**
 *
 * @author Roger
 */
public interface SessaoDao extends DaoMain<Sessao>{   
    public Sessao localizarPorHorario(LocalTime horario, String sala_codigo);
    public int ingressosVendidos(int sessao_id, int sala_id);
}
