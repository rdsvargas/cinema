package dao;

import java.util.List;
import model.Filme;

/**
 *
 * @author Roger
 */
public interface FilmeDao extends DaoMain<Filme> {

    /**
     *
     * @param filme_id Identificador do filme
     * @return Object Filme
     */
    public Filme localizarPorId(int filme_id);
    
    public List<Filme> listaFilmeBySessao();
}
