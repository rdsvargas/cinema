package view.menu;

public class SessaoMenu {

    public static final int OP_CADASTRAR = 1;
    public static final int OP_ALTERAR = 2;
    public static final int OP_LISTAR = 3;
    public static final int OP_VOLTAR = 0;

    public static String getOpcoes() {
        return ("\n--------------------------\n"
                + "1 - Cadastrar Sessão\n"
                + "2 - Alterar Sessão\n"
                + "3 - Listar Sessão\n"
                + "0 - Sair"
                + "\n--------------------------\n");
    }

}
