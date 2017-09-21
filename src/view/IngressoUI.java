package view;

import java.time.LocalTime;
import model.Ingresso;
import repositorio.RepositorioIngressos;
import repositorio.RepositorioSessoes;
import model.Sessao;
import util.Console;
import view.menu.IngressoMenu;

public class IngressoUI {

    private RepositorioIngressos lista;
    private RepositorioSessoes listaSessoes;

    public IngressoUI(RepositorioIngressos lista, RepositorioSessoes listaSessoes) {
        this.lista = lista;
        this.listaSessoes = listaSessoes;
    }

    public void executar() {
        int opcao = 0;
        do {
            System.out.println(IngressoMenu.getOpcoes());
            opcao = Console.scanInt("Digite sua opção: ");
            switch (opcao) {
                case IngressoMenu.OP_CADASTRAR:
                    venderIngresso();
                    break;
                case IngressoMenu.OP_LISTAR:
                    vendaPorSecao();
                    break;
                case IngressoMenu.OP_VOLTAR:
                    break;
                default:
                    System.out.println("Opção inválida..");
            }
        } while (opcao != IngressoMenu.OP_VOLTAR);
    }

    public void venderIngresso() {
        LocalTime hora = Console.scanTime("Informe hora da Sessão (hh:mm): ");
        if (hora == null) {
            System.out.println("Hora informada inválida.");
        } else {
            Sessao sessao = listaSessoes.buscaSessao(hora);
            if (sessao == null) {
                System.out.println("Sessão informada inválida");
            } else {
                if (lista.getListaIngressos().isEmpty()) {
                    lista.addIngresso(new Ingresso(1, sessao));
                } else {
                    if (!lista.venderIngresso(hora)) {
                        System.out.println("Ingressos esgotados para esta sessão");
                    }
                }
            }
        }
    }

    public void vendaPorSecao() {
        if (lista.getListaIngressos().size() <= 0) {
            System.out.println("-----------------------------------------------------");
            System.out.println("Não foram efetuadas nenhuma venda de ingressos ainda.");
            System.out.println("-----------------------------------------------------\n");
        } else {
            System.out.println(String.format("%-10s", "Ingressos") + 
                    String.format("%-8s", "Sessão") + 
                    String.format("%-30s", "Filme") + 
                    String.format("%-15s", "Assentos Vagos"));

            for (Ingresso ingresso : lista.getListaIngressos()) {
                System.out.println(String.format("%-10s", ingresso.getIngressosVendidos()) +
                        String.format("%-8s", ingresso.getSessao().getHora()) + 
                        String.format("%-30s", ingresso.getSessao().getFilme().getNome()) +
                        String.format("%-15s", ingresso.getSessao().getSala().getQtdAssentos() - ingresso.getIngressosVendidos()));
            }
        }
    }
}
