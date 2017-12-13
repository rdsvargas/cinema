/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dao.IngressoDao;
import dao.db.IngressoDaoBd;
import java.util.List;
import model.Ingresso;

/**
 *
 * @author Roger
 */
public class IngressoNegocio {

    private IngressoDao ingressoDao;

    /**
     *
     */
    public IngressoNegocio() {
        this.ingressoDao = new IngressoDaoBd();
    }

    /**
     *
     * @param ingresso
     * @param qtd_vendido
     */
    public void salvar(Ingresso ingresso, int qtd_vendido) {
        this.ingressoDao.salvar(ingresso, qtd_vendido);
    }

    /**
     *
     * @return
     */
    public List<Ingresso> listar() {
        return this.ingressoDao.listar();
    }   
    
    public List<Ingresso> listaTableView() {
        return this.ingressoDao.listaTableView();
    }   
    
    public void deletar(Ingresso ingresso){
        this.ingressoDao.deletar(ingresso);
    }
}
