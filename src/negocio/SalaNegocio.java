package negocio;

import dao.SalaDao;
import dao.db.SalaDaoBd;
import java.util.ArrayList;
import java.util.List;
import model.Sala;
import util.Console;
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
    public SalaNegocio() {
        this.salaDao = new SalaDaoBd();
    }

    /**
     *
     * @param sala
     * @throws ValidaDataException
     */
    public void salvar(Sala sala) throws ValidaDataException, Exception {
        // validação dos dados informados
        ValidaDados.validaEntrada(sala.getCodigo(), "Identificacao da Sala", 10);

        if (this.salaDao.localizarPorCodigo(sala.getCodigo(), false) == null) {
            this.salaDao.salvar(sala);
        } else {
            throw new Exception("Sala já cadastrada");
        }

    }

    /**
     *
     * @param sala
     * @throws ValidaDataException
     */
    public void atualizar(Sala sala) throws ValidaDataException, Exception {
        // validação dos dados informados
        ValidaDados.validaEntrada(sala.getCodigo(), "Identificação da Sala", 10);

        Sala sl = this.salaDao.localizarPorCodigo(sala.getCodigo(), false);
        if (sl == null || sl.getId() == sala.getId()) {
            this.salaDao.atualizar(sala);
        } else {
            throw new Exception("Sala já cadastrada");
        }
    }

    public void deletar(Sala sala) {
        this.salaDao.deletar(sala);
    }

    /**
     *
     * @param codigo
     * @return
     */
    public Sala localizarPorCodigo(String codigo) {
        return salaDao.localizarPorCodigo(codigo, true);
    }

    /**
     *
     * @param id
     * @return
     */
    public Sala localizarPorId(int id) {
        return salaDao.localizarPorId(id);
    }

    /**
     *
     * @return
     */
    public List<Sala> listar() {
        return salaDao.listar();
    }

//    public List<String> listaSala() {
//        List<Sala> listaSalas = salaDao.listar();
//        List<String> result = new ArrayList<>();
//        for (Sala sala : listaSalas) {
//            result.add(Console.formatString(sala.getId(), 5) + " | " + sala.getCodigo());
//        }
//        return result;
//    }

}
