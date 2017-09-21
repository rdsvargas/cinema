package view.menu;

public class FilmeMenu {

    public static final int OP_CADASTRAR = 1;
    public static final int OP_LISTAR = 2;
    public static final int OP_VOLTAR = 0;

    public static String getOpcoes() {
        return ("\n--------------------------\n"
              + "1 - Cadastrar Filmes\n"
              + "2 - Listar Filmes\n"
              + "0 - Sair"
              + "\n--------------------------\n");
    }

}
