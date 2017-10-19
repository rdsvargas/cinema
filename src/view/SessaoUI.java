package view;

import repositorio.RepositorioSessoes;
import repositorio.RepositorioSalas;
import repositorio.RepositorioFilmes;
import view.menu.SessaoMenu;
import java.time.LocalTime;
import model.Filme;
import model.Sala;
import model.Sessao;
import util.Console;

public class SessaoUI {

    private RepositorioSessoes lista;
    private RepositorioFilmes listaFilmes;
    private RepositorioSalas listaSalas;

    public SessaoUI(RepositorioSessoes lista, RepositorioFilmes listaFilmes, RepositorioSalas listaSalas) {
        this.lista = lista;
        this.listaFilmes = listaFilmes;
        this.listaSalas = listaSalas;
    }

    public void executar() {
        int opcao = 0;
        do {
            System.out.println(SessaoMenu.getOpcoes());
            opcao = Console.scanInt("Digite sua opção: ");
            switch (opcao) {
                case SessaoMenu.OP_CADASTRAR:
                    cadastrarSessao();
                    break;
                case SessaoMenu.OP_LISTAR:
                    listarSessoes();
                    break;
                case SessaoMenu.OP_VOLTAR:
                    break;
                default:
                    System.out.println("Opção inválida..");
            }
        } while (opcao != SessaoMenu.OP_VOLTAR);
    }

    private void cadastrarSessao() {
        String msgError = "";
        LocalTime hora = Console.scanTime("Informe hora da Sessão (hh:mm): ");
        msgError = hora == null ? "Hora informada inválida.\n" : "";

        Filme filme = listaFilmes.buscarFilmePorCodigo(Console.scanInt("Informe o código do Filme: "));
        msgError = filme == null ? msgError + "Filme informado inválido.\n" : msgError;

        Sala sala = listaSalas.buscarSala(Console.scanInt("Informe o número da Sala: "));
        msgError = sala == null ? msgError + "Sala informada inválida." : msgError;
        if (msgError.isEmpty()) {
            lista.addSessao(new Sessao(hora, sala, filme));
        } else {
            System.out.println(msgError);
        }
    }

    public void listarSessoes() {
        for (Sessao sessao : lista.getListaSessoes()) {
            System.out.println(sessao.getHora() + " -" + sessao.getFilme().getNome() + " - " + sessao.getSala().getNumeroSala());
        }
    }
}
