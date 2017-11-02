package negocio;

import dao.FilmeDao;
import dao.db.FilmeDaoBd;
import java.util.List;
import model.Filme;

/**
 *
 * @author Roger
 */
public class FilmeNegocio {

    private FilmeDao filmeDao;

    public FilmeNegocio() {
        this.filmeDao = new FilmeDaoBd();
    }

    public void salvar(Filme filme) throws NegocioException {
        // validação dos dados informados
        this.validaData(filme.getNome(), "Nome do Filme", 30);
        this.validaData(filme.getGenero(), "Gênero", 20);
        this.validaData(filme.getSinopsia(), "Sinópsia", 50);
        this.filmeDao.salvar(filme);
    }

    public void atualizar(Filme filme) throws NegocioException {
        // validação dos dados informados
        this.validaData(filme.getNome(), "Nome do Filme", 30);
        this.validaData(filme.getGenero(), "Gênero", 20);
        this.validaData(filme.getSinopsia(), "Sinópsia", 50);
        this.filmeDao.atualizar(filme);
    }
    
    public Filme localizarPorId(int id) throws NegocioException{
        return this.filmeDao.localizarPorId(id);
    }
    
    public List<Filme> listar(){
        return filmeDao.listar();
    }
    
    private void validaData(Object data, String desc, int size) throws NegocioException {
        if (data instanceof String) {
            if (((String) data).length() > size) {
                throw new NegocioException(desc + " não pode ser maior que " + size + " caracteres.");
            } else if (((String) data).isEmpty()) {
                throw new NegocioException(desc + " inválido.");
            }
        } else {
            if ((int) data <= 0) {
                throw new NegocioException(desc + " inválido");
            }
        }
    }

}
