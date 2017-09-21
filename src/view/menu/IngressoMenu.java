package view.menu;

public class IngressoMenu {
   public static final int OP_CADASTRAR = 1;
    public static final int OP_LISTAR = 2;
    public static final int OP_VOLTAR = 0;

    public static String getOpcoes() {
        return ("\n--------------------------\n"
              + "1 - Venda de Ingressos\n"
              + "2 - Vendas por Sessão\n"
              + "0 - Sair"
              + "\n--------------------------\n");
    }

}
