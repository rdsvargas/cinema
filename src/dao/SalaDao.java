/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Sala;

/**
 *
 * @author Roger
 */
public interface SalaDao extends DaoMain<Sala>{ 
    public Sala localizarPorCodigo(String codigo);
    public Sala localizarPorId(int id);
}
