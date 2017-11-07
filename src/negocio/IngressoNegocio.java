/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dao.IngressoDao;
import dao.db.IngressoDaoBd;
import model.Ingresso;

/**
 *
 * @author Roger
 */
public class IngressoNegocio {
    private IngressoDao ingressoDao;
    
    public IngressoNegocio(){
        this.ingressoDao = new IngressoDaoBd();
    }
    
    public void salvar(Ingresso ingresso, int qtd_vendido){
        this.ingressoDao.salvar(ingresso, qtd_vendido);
    }
}
