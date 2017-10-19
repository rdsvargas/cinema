
package dao;

import model.Filme;

/**
 *
 * @author Roger
 */
public interface FilmeDAO {
    public void salvar(Filme filme);
    public void deletar(Filme filme);
    public void atualizar(Filme filme);
}
