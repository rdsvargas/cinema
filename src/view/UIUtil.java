/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import util.Console;

/**
 *
 * @author roger
 */
public class UIUtil {

    public static final String SIM = "sim";
    public static final String NAO = "nao";
    
    public static void mostrarErro(Object msgErro) {
        System.out.println("\n*********");
        System.out.println("* " +msgErro);
        System.out.println("*********");
    }
    
    public static boolean getConfirmacao(Object msg) {
        String confirmacao = "NAO";
        do {
            confirmacao = Console.scanString(msg+"(sim/nao)");
            if (confirmacao.equalsIgnoreCase(SIM)) {
                return true;
            }
            else if(confirmacao.equalsIgnoreCase(NAO)){
                return false;
            }
            else{
                System.out.println("Opcao invalida!");
            }                
        }while(confirmacao.equalsIgnoreCase(SIM) || 
                confirmacao.equalsIgnoreCase(NAO));
        return false;
    } 
    
}
