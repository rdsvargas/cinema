package view;

import view.menu.SessaoMenu;
import java.time.LocalTime;
import java.util.List;
import model.Filme;
import model.Sala;
import model.Sessao;
import negocio.FilmeNegocio;
import negocio.SalaNegocio;
import negocio.SessaoNegocio;
import util.Console;

public class SessaoUI {

    private SessaoNegocio sessaoNegocio;
    private FilmeNegocio filmeNegocio;
    private SalaNegocio salaNegocio;

    public SessaoUI() {
        sessaoNegocio = new SessaoNegocio();
        filmeNegocio = new FilmeNegocio();
        salaNegocio = new SalaNegocio();
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
                case SessaoMenu.OP_ALTERAR:
                    alterarSessao();
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
        try {
            LocalTime hora = Console.scanTime("Informe hora da Sessão (hh:mm): ");
            Filme filme = filmeNegocio.localizarPorId(Console.scanInt("Informe o Código do filme: "));
            Sala sala = salaNegocio.localizarPorCodigo(Console.scanString("Identificação Sala: "));

            Sessao sessao = new Sessao(hora, sala, filme);
            sessaoNegocio.salvar(sessao);
            System.out.println(sessao.toString() + " -> cadastrada com sucesso!");
        } catch (Exception ex) {
            UIUtil.mostrarErro(ex.getMessage());
        }
    }

    private void alterarSessao() {
        try {
            LocalTime hora = Console.scanTime("Informe hora da Sessão (hh:mm): ");
            String sala_codigo = Console.scanString("Identificação Sala: ");

            Sessao sessao = sessaoNegocio.localizarPorHorario(hora, sala_codigo);
            System.out.println(sessao.toString() + "\n");
            
            hora = Console.scanTime("Informe novo horário da Sessão (hh:mm): ");
            int filme_id = Console.scanInt("Informe novo Filme: ");
            
            Filme filme = filmeNegocio.localizarPorId(filme_id);
            sessao.setHora(hora);
            sessao.setFilme(filme);
            
            sessaoNegocio.atualizar(sessao);
            System.out.println("\n" + sessao.toString());
        } catch (Exception ex) {
            UIUtil.mostrarErro(ex.getMessage());
        }
    }

    public void listarSessoes() {
        List<Sessao> listaSessoes = sessaoNegocio.listar();
        for (Sessao sessao : listaSessoes) {
            System.out.println(sessao.toString());
        }
    }
}
