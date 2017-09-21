package view;

import repositorio.RepositorioFilmes;
import repositorio.RepositorioSalas;
import repositorio.RepositorioSessoes;
import repositorio.RepositorioIngressos;
import util.Console;
import view.menu.MainMenu;

public class MainUI {

    private RepositorioFilmes listaFilmes;
    private RepositorioSalas listaSalas;
    private RepositorioSessoes listaSessoes;
    private RepositorioIngressos listaIngressos;

    public MainUI() {
        listaFilmes = new RepositorioFilmes();
        listaSalas = new RepositorioSalas();
        listaSessoes = new RepositorioSessoes();
        listaIngressos = new RepositorioIngressos();
    }

    public void executar() {
        int opcao = 0;
        do {

            System.out.println(MainMenu.getOpcoes());
            opcao = Console.scanInt("Digite sua opção: ");
            switch (opcao) {
                case MainMenu.OP_FILMES:
                    new FilmeUI(listaFilmes).executar();
                    break;
                case MainMenu.OP_SALAS:
                    new SalaUI(listaSalas).executar();
                    break;
                case MainMenu.OP_SESSAO:
                    new SessaoUI(listaSessoes, listaFilmes, listaSalas).executar();
                    break;
                case MainMenu.OP_INGRESSOS:
                    new IngressoUI(listaIngressos, listaSessoes).executar();
                    break;
            }
        } while (opcao != MainMenu.OP_SAIR);
    }
}