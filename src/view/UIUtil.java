/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.JOptionPane;
import util.Console;

/**
 *
 * @author roger
 */
public class UIUtil {

    /**
     *
     */
    public static final String SIM = "sim";

    /**
     *
     */
    public static final String NAO = "nao";

    /**
     *
     * @param msgErro
     */
    public static void mostrarErro(Object msgErro) {
        System.out.println("\n*********");
        System.out.println("* " + msgErro);
        System.out.println("*********");
    }

    /**
     *
     * @param msgErro
     * @param messageType JOptionPane.ERROR_MESSAGE, JOptionPane.INFORMATION_MESSAGE, JOptionPane.WARNING_MESSAGE, JOptionPane.QUESTION_MESSAGE
     */
    public static void exibeErro(Object msgErro, int messageType) {
        String title;
        switch (messageType) {
            case JOptionPane.ERROR_MESSAGE:
                title = "Erro";
                break;
            case JOptionPane.INFORMATION_MESSAGE:
                title = "Informação";
                break;
            case JOptionPane.WARNING_MESSAGE:
                title = "Alerta";
                break;
            case JOptionPane.QUESTION_MESSAGE:
                title = "??";
                break;
            default:
                title = "";
        }
        JOptionPane.showMessageDialog(null, msgErro, title, JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     *
     * @param msg
     * @return
     */
    public static boolean getConfirmacao(Object msg) {
        String confirmacao = "NAO";
        do {
            confirmacao = Console.scanString(msg + "(sim/nao)");
            if (confirmacao.equalsIgnoreCase(SIM)) {
                return true;
            } else if (confirmacao.equalsIgnoreCase(NAO)) {
                return false;
            } else {
                System.out.println("Opcao invalida!");
            }
        } while (confirmacao.equalsIgnoreCase(SIM)
                || confirmacao.equalsIgnoreCase(NAO));
        return false;
    }

}
