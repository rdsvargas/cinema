package view;

import view.menu.FilmeMenu;
import java.util.Collections;
import java.util.List;
import model.Filme;
import negocio.FilmeNegocio;
import util.Console;
import util.ValidaDataException;

public class FilmeUI {

    private FilmeNegocio filmeNegocio;

    public FilmeUI() {
        filmeNegocio = new FilmeNegocio();
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
                case FilmeMenu.OP_ALTERAR:
                    alterarFilme();
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
        String nome = Console.scanString("Filme: ");
        String genero = Console.scanString("Genero: ");
        String sinopsia = Console.scanString("Sinópsia: ");
        try {
            Filme filme = new Filme(nome, genero, sinopsia);
            filmeNegocio.salvar(filme);
            System.out.println(filme.toString() + " -> cadastrado com sucesso!");
        } catch (ValidaDataException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        }
    }

    private void alterarFilme() {
        int filme_id = Console.scanInt("Informe o Código do filme: ");
        try {
            Filme filme = filmeNegocio.localizarPorId(filme_id);

            System.out.println(filme.toString() + "-> filme ");

            String nome = Console.scanString("Filme: ");
            String genero = Console.scanString("Genero: ");
            String sinopsia = Console.scanString("Sinópsia: ");

            filme.setNome(nome);
            filme.setGenero(genero);
            filme.setSinopsia(sinopsia);
            
            filmeNegocio.atualizar(filme);
        } catch (Exception ex) {
            UIUtil.mostrarErro(ex.getMessage());
        }
    }

    private void listaFilmes() {
        List<Filme> listaFilmes = filmeNegocio.listar();

        if (listaFilmes.isEmpty()) {
            System.out.println("--------------------------------");
            System.out.println("Nao existem filmes cadastrados");
            System.out.println("--------------------------------\n");
        } else {
            System.out.println(String.format("%-10s", "Código")
                    + String.format("%-31s", "|FILME")
                    + String.format("%-21s", "|GÊNERO")
                    + String.format("%-51s", "|SINÓPSIA"));

            for (Filme filme : listaFilmes) {
                System.out.println(this.linhaSeparadora());

                System.out.println(String.format("%-10s", filme.getId())
                        + String.format("%-31s", "|" + filme.getNome())
                        + String.format("%-21s", "|" + filme.getGenero())
                        + String.format("%-51s", "|" + filme.getSinopsia()));
            }
            System.out.println(this.linhaSeparadora());
        }
    }

    private String linhaSeparadora() {
        return (String.format("%-10s", String.join("", Collections.nCopies(10, "-")))
                + String.format("%-31s", "+" + String.join("", Collections.nCopies(29, "-")))
                + String.format("%-21s", "+" + String.join("", Collections.nCopies(19, "-")))
                + String.format("%-51s", "+" + String.join("", Collections.nCopies(49, "-"))));
    }
}
