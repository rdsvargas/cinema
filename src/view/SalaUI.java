package view;

import repositorio.RepositorioSalas;
import java.util.Collections;
import model.Sala;
import util.Console;
import view.menu.SalaMenu;

public class SalaUI {

    private RepositorioSalas lista;

    public SalaUI(RepositorioSalas lista) {
        this.lista = lista;
    }

    public void executar() {
        int opcao = 0;
        do {
            System.out.println(SalaMenu.getOpcoes());
            opcao = Console.scanInt("Digite sua opção: ");
            switch (opcao) {
                case SalaMenu.OP_CADASTRAR:
                    cadastrarSala();
                    break;
                case SalaMenu.OP_LISTAR:
                    listaSalas();
                    break;
                case SalaMenu.OP_VOLTAR:
                    break;
                default:
                    System.out.println("Opção inválida..");
            }
        } while (opcao != SalaMenu.OP_VOLTAR);
    }

    private void cadastrarSala() {
        int numeroSala = Console.scanInt("Número da Sala: ");
        if (numeroSala == -1) {
            System.out.println("Informe um valor numérico inteiro positivo para a sala");
        } else {
            if (lista.salaExiste(numeroSala)) {
                System.out.println("Sala " + numeroSala + " já cadastrada.");
            } else {
                int qtdAssentos = Console.scanInt("Quantidade de Assentos: ");
                if (qtdAssentos == -1) {
                    System.out.println("Informe um valor numérico inteiro positivo para a quantidade de assentos");
                } else {
                    lista.addSala(new Sala(numeroSala, qtdAssentos));
                }
            }
        }
    }

    public void listaSalas() {
        if (lista.getListaSala().size() <= 0) {
            System.out.println("--------------------------------");
            System.out.println("Nao existem filmes cadastrados");
            System.out.println("--------------------------------\n");
        } else {
            System.out.println(String.format("%-10s", "Sala") + String.format("%-12s", "|Qtd Assentos"));
            for (Sala sala : lista.getListaSala()) {
                System.out.println(linhaSeparadora());
                System.out.println(String.format("%-10s", sala.getNumeroSala())
                        + String.format("%-12s", "|" + sala.getQtdAssentos()));
            }
            System.out.println(linhaSeparadora());
        }
    }

    private String linhaSeparadora() {
        return String.format("%-10s", String.join("", Collections.nCopies(10, "-")))
                + String.format("%-12s", "+" + String.join("", Collections.nCopies(12, "-")));

    }
}
