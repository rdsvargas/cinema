package dao;

import java.util.List;

/**
 *
 * @author Roger
 * @param <T>
 */
public interface DaoMain<T> {

    /**
     *
     * @param dominio
     */
    public void salvar(T dominio);

    /**
     *
     * @param dominio
     */
    public void deletar(T dominio);

    /**
     *
     * @param dominio
     */
    public void atualizar(T dominio);

    /**
     *
     * @return
     */
    public List<T> listar();
}
