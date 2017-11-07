package dao;

import model.Filme;

/**
 *
 * @author Roger
 */
public interface FilmeDao extends DaoMain<Filme> {
    public Filme localizarPorId(int filme_id);
}
