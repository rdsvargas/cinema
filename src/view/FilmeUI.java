package view;

import view.menu.FilmeMenu;
import repositorio.RepositorioFilmes;
import java.util.Collections;
import java.util.List;
import model.Filme;
import negocio.FilmeNegocio;
import negocio.NegocioException;
import util.Console;

public class FilmeUI {

    private RepositorioFilmes lista;

    private FilmeNegocio filmeNegocio;

    public FilmeUI(RepositorioFilmes lista) {
        this.lista = lista;
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

    public void listaFilmes() {
        List<Filme> listaFilmes = filmeNegocio.listar();
        this.listaFilmes(listaFilmes);
    }

    private void cadastrarFilme() {
        String nome = Console.scanString("Filme: ");
        String genero = Console.scanString("Genero: ");
        String sinopsia = Console.scanString("Sinópsia: ");
        try {
            Filme filme = new Filme(nome, genero, sinopsia);
            filmeNegocio.salvar(filme);
            //System.out.println("Paciente " + nome + " cadastrado com sucesso!");
            System.out.println(filme.toString() + " -> cadastrado com sucesso!");
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        }
    }

    private void alterarFilme() {
        int codigo = Console.scanInt("Informe o Código do filme: ");
        try {
            Filme filme = filmeNegocio.localizarPorId(codigo);

            if (filme == null) {
                System.out.println("\nFilme não encontrado");
                return;
            }

            System.out.println(filme.toString() + "-> filme ");

            String nome = Console.scanString("Filme: ");
            String genero = Console.scanString("Genero: ");
            String sinopsia = Console.scanString("Sinópsia: ");

            filme.setNome(nome);
            filme.setGenero(genero);
            filme.setSinopsia(sinopsia);
            
            filmeNegocio.atualizar(filme);
            

        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        }
    }

    private void listaFilmes(List<Filme> listaFilmes) {
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

                System.out.println(String.format("%-10s", filme.getCodigo())
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
