/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Roger
 */
public class ValidaDados {

    /**
     *
     * @param data
     * @param desc
     * @param size
     * @throws ValidaDataException
     */
    public static  void validaEntrada(Object data, String desc, int size) throws ValidaDataException {
        if (data instanceof String) {
            if (((String) data).length() > size) {
                throw new ValidaDataException(desc + " não pode ser maior que " + size + " caracteres.");
            } else if (((String) data).isEmpty()) {
                throw new ValidaDataException(desc + " inválido.");
            }
        } else {
            if ((int) data <= 0) {
                throw new ValidaDataException(desc + " inválido");
            }
        }
    }
    
}
