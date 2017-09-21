package view;

import view.menu.FilmeMenu;
import repositorio.RepositorioFilmes;
import java.util.Collections;
import model.Filme;
import util.Console;

public class FilmeUI {

    private RepositorioFilmes lista;

    public FilmeUI(RepositorioFilmes lista) {
        this.lista = lista;
    }

    public void executar() {
        int opcao = 0;
        do {
            System.out.println(FilmeMenu.getOpcoes());
            opcao = Console.scanInt("Digite sua opção: ");
            switch (opcao) {
                case FilmeMenu.OP_CADASTRAR:
                    cadastrarFilme();
                    break;
                case FilmeMenu.OP_LISTAR:
                    listaFilmes();
                    break;
                case FilmeMenu.OP_VOLTAR:
                    break;
                default:
                    System.out.println("Opção inválida..");
            }
        } while (opcao != FilmeMenu.OP_VOLTAR);
    }

    private void cadastrarFilme() {
        int codigo = lista.proximoCodigo();
        System.out.println("Código Filme: " + codigo);
        String nome = Console.scanString("Filme: ");
        if (lista.filmeExiste(nome)) {
            System.out.println("Filme " + nome + " já está cadastrado");
        } else {
            try {
                String genero = Console.scanString("Genero: ");
                String sinopsia = Console.scanString("Sinópsia: ");
                lista.addFilme(new Filme(codigo, nome, genero, sinopsia));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void listaFilmes() {
        if (lista.getListaFilmes().size() <= 0) {
            System.out.println("--------------------------------");
            System.out.println("Nao existem filmes cadastrados");
            System.out.println("--------------------------------\n");
        } else {
            System.out.println(String.format("%-10s", "Código") + 
                    String.format("%-31s", "|FILME") + 
                    String.format("%-21s", "|GÊNERO") + 
                    String.format("%-51s", "|SINÓPSIA"));

            for (Filme filme : lista.getListaFilmes()) {
                System.out.println(this.linhaSeparadora());

                System.out.println(String.format("%-10s", filme.getCodigo()) +
                        String.format("%-31s", "|" + filme.getNome()) +
                        String.format("%-21s", "|" + filme.getGenero()) +
                        String.format("%-51s", "|" + filme.getSinopsia()));
            }
            System.out.println(this.linhaSeparadora());
        }
    }

    private String linhaSeparadora() {
        return (String.format("%-10s", String.join("", Collections.nCopies(10, "-"))) +
                String.format("%-31s", "+" + String.join("", Collections.nCopies(29, "-"))) +
                String.format("%-21s", "+" + String.join("", Collections.nCopies(19, "-"))) +
                String.format("%-51s", "+" + String.join("", Collections.nCopies(49, "-"))));
    }
}
