package view.menu;

/**
 *
 * @author Roger
 */
public class IngressoMenu {

    /**
     *
     */
    public static final int OP_CADASTRAR = 1;

    /**
     *
     */
    public static final int OP_LISTAR = 2;

    /**
     *
     */
    public static final int OP_VOLTAR = 0;

    /**
     *
     * @return
     */
    public static String getOpcoes() {
        return ("\n--------------------------\n"
              + "1 - Venda de Ingressos\n"
              + "2 - Listar Ingressos\n"
              + "0 - Sair"
              + "\n--------------------------\n");
    }

}
