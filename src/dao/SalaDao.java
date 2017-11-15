package dao;

import model.Sala;

/**
 *
 * @author Roger
 */
public interface SalaDao extends DaoMain<Sala>{ 

    /**
     *
     * @param codigo Codigo da sala
     * @return Object Sala
     */
    public Sala localizarPorCodigo(String codigo);

    /**
     *
     * @param id Identificador da sala
     * @return Object Sala
     */
    public Sala localizarPorId(int id);
}
