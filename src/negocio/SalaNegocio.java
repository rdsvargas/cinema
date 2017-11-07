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
    
    public SalaNegocio(){
        this.salaDao = new SalaDaoBd();
    }
    
    public void salvar(Sala sala) throws ValidaDataException{
        // validação dos dados informados
        ValidaDados.validaEntrada(sala.getCodigo(), "Identificacao da Sala", 10);
        
        this.salaDao.salvar(sala);
    }
    
    public void atualizar(Sala sala) throws ValidaDataException{
        // validação dos dados informados
        ValidaDados.validaEntrada(sala.getCodigo(), "Identificação da Sala", 10);
        this.salaDao.atualizar(sala);
    }
    
    public Sala localizarPorCodigo(String codigo){
        return salaDao.localizarPorCodigo(codigo);
    }

    public Sala localizarPorId(int id){
        return salaDao.localizarPorId(id);
    }
    
    public List<Sala> listar(){
        return salaDao.listar();
    }
}
