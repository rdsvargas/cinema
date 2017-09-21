package view.menu;

public class SalaMenu {

    public static final int OP_CADASTRAR = 1;
    public static final int OP_LISTAR = 2;
    public static final int OP_VOLTAR = 0;

    public static String getOpcoes() {
        return ("\n--------------------------\n"
                + "1 - Cadastrar Salas\n"
                + "2 - Listar Salas\n"
                + "0 - Sair"
                + "\n--------------------------\n");
    }

}
