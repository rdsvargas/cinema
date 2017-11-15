package view.menu;

/**
 *
 * @author Roger
 */
public class MainMenu {

    /**
     *
     */
    public static final int OP_SALAS = 2;

    /**
     *
     */
    public static final int OP_FILMES = 1;

    /**
     *
     */
    public static final int OP_SESSAO = 3;

    /**
     *
     */
    public static final int OP_INGRESSOS = 4;

    /**
     *
     */
    public static final int OP_SAIR = 0;

    /**
     *
     * @return
     */
    public static String getOpcoes() {
        return ("\n--------------------------\n"
              + "1 - Menu Filmes\n"
              + "2 - Menu Salas\n"
              + "3 - Menu Sessão\n"
              + "4 - Menu Ingressos\n"
              + "0 - Sair"
              + "\n--------------------------\n");
    }
}
