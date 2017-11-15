package negocio;

import dao.FilmeDao;
import dao.db.FilmeDaoBd;
import java.util.List;
import model.Filme;
import util.ValidaDados;
import util.ValidaDataException;

/**
 *
 * @author Roger
 */
public class FilmeNegocio {

    private FilmeDao filmeDao;

    /**
     *
     */
    public FilmeNegocio() {
        this.filmeDao = new FilmeDaoBd();
    }

    /**
     *
     * @param filme
     * @throws ValidaDataException
     */
    public void salvar(Filme filme) throws ValidaDataException {
        // validação dos dados informados
        ValidaDados.validaEntrada(filme.getNome(), "Nome do Filme", 30);
        ValidaDados.validaEntrada(filme.getGenero(), "Gênero", 20);
        ValidaDados.validaEntrada(filme.getSinopsia(), "Sinópsia", 50);
        this.filmeDao.salvar(filme);
    }

    /**
     *
     * @param filme
     * @throws ValidaDataException
     */
    public void atualizar(Filme filme) throws ValidaDataException {
        // validação dos dados informados
        ValidaDados.validaEntrada(filme.getNome(), "Nome do Filme", 30);
        ValidaDados.validaEntrada(filme.getGenero(), "Gênero", 20);
        ValidaDados.validaEntrada(filme.getSinopsia(), "Sinópsia", 50);
        this.filmeDao.atualizar(filme);
    }
    
    /**
     *
     * @param id
     * @return
     */
    public Filme localizarPorId(int id) {
        return this.filmeDao.localizarPorId(id);
    }
    
    /**
     *
     * @return
     */
    public List<Filme> listar(){
        return filmeDao.listar();
    }
}
