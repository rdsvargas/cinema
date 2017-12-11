package negocio;

import dao.SalaDao;
import dao.db.SalaDaoBd;
import java.util.List;
import model.Sala;
import util.ValidaDados;
import util.ValidaDataException;

/**
 *
 * @author Roger
 */
public class SalaNegocio {
    private SalaDao salaDao;
    
    /**
     *
     */
    public SalaNegocio(){
        this.salaDao = new SalaDaoBd();
    }
    
    /**
     *
     * @param sala
     * @throws ValidaDataException
     */
    public void salvar(Sala sala) throws ValidaDataException{
        // validação dos dados informados
        ValidaDados.validaEntrada(sala.getCodigo(), "Identificacao da Sala", 10);
        
        this.salaDao.salvar(sala);
    }
    
    /**
     *
     * @param sala
     * @throws ValidaDataException
     */
    public void atualizar(Sala sala) throws ValidaDataException{
        // validação dos dados informados
        ValidaDados.validaEntrada(sala.getCodigo(), "Identificação da Sala", 10);
        this.salaDao.atualizar(sala);
    }
    
    public void deletar(Sala sala){
        this.salaDao.deletar(sala);
    }
    
    /**
     *
     * @param codigo
     * @return
     */
    public Sala localizarPorCodigo(String codigo){
        return salaDao.localizarPorCodigo(codigo);
    }

    /**
     *
     * @param id
     * @return
     */
    public Sala localizarPorId(int id){
        return salaDao.localizarPorId(id);
    }
    
    /**
     *
     * @return
     */
    public List<Sala> listar(){
        return salaDao.listar();
    }
}
