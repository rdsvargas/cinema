package view.menu;

/**
 *
 * @author Roger
 */
public class FilmeMenu {

    /**
     *
     */
    public static final int OP_CADASTRAR = 1;

    /**
     *
     */
    public static final int OP_ALTERAR = 2;

    /**
     *
     */
    public static final int OP_LISTAR = 3;

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
              + "1 - Cadastrar Filme\n"
              + "2 - Alterar Filme\n"
              + "3 - Listar Filme\n"
              + "0 - Sair"
              + "\n--------------------------\n");
    }

}
