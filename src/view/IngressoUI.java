package view;

import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import model.Ingresso;
import model.Sessao;
import negocio.IngressoNegocio;
import negocio.SessaoNegocio;
import util.Console;
import util.ValidaDataException;
import view.menu.IngressoMenu;

public class IngressoUI {

    private IngressoNegocio ingressoNegocio;
    private SessaoNegocio sessaoNegocio;

    public IngressoUI() {
        this.ingressoNegocio = new IngressoNegocio();
        this.sessaoNegocio = new SessaoNegocio();
    }

    public void executar() throws ValidaDataException {
        int opcao = 0;
        do {
            System.out.println(IngressoMenu.getOpcoes());
            opcao = Console.scanInt("Digite sua opção: ");
            switch (opcao) {
                case IngressoMenu.OP_CADASTRAR:
                    venderIngresso();
                    break;
                case IngressoMenu.OP_LISTAR:
                    listarIngressos();
                    break;
                case IngressoMenu.OP_VOLTAR:
                    break;
                default:
                    System.out.println("Opção inválida..");
            }
        } while (opcao != IngressoMenu.OP_VOLTAR);
    }

    public void venderIngresso() throws ValidaDataException {
        LocalTime hora = Console.scanTime("Informe hora da Sessão (hh:mm): ");
        if (hora == null) {
            System.out.println("Hora informada inválida.");
        } else {
            try {
                String sala_codigo = Console.scanString("Identificação Sala: ");
                Sessao sessao = sessaoNegocio.localizarPorHorario(hora, sala_codigo);
                int saldo_ingressos = sessao.getSala().getQtdAssentos() - sessaoNegocio.ingressosVendidos(sessao.getId(), sessao.getSala().getId());
                System.out.println("Saldo de ingressos: " + saldo_ingressos);

                int qtd_ingressos = Console.scanInt("Informe a quandidade de ingressos a vender: ");

                if (qtd_ingressos <= saldo_ingressos) {
                    Ingresso ingresso = new Ingresso(sessao);
                    ingressoNegocio.salvar(ingresso, qtd_ingressos);
                } else {
                    throw new ValidaDataException("Saldo de ingressos insuficiente para atender a solicitação.");
                }
            } catch (Exception ex) {
                UIUtil.mostrarErro(ex.getMessage());
            }
        }
    }

    private void listarIngressos() {
        List<Ingresso> listaIngressos = ingressoNegocio.listar();

        if (listaIngressos.isEmpty()) {
            System.out.println("-----------------------------------");
            System.out.println("Nao foram vendidos nenhum ingresso");
            System.out.println("-----------------------------------\n");
        } else {
            linhaSeparadora();
            System.out.println(String.format("%-7s", "|Hora" )
                    + String.format("%-12s", "|Sala")
                    + String.format("%-32s", "|Filme")
                    + String.format("%-8s", "|Vendidos")
                    + String.format("%-5s", "|Saldo|")
            );
            linhaSeparadora();
            for (Ingresso ingresso : listaIngressos) {
                System.out.println(String.format("%-7s", "|" + ingresso.getSessao().getHora())
                        + String.format("%-12s", "|" + ingresso.getSessao().getSala().getCodigo())
                        + String.format("%-32s", "|" + ingresso.getSessao().getFilme().getNome())
                        + "|" + String.format("%8d", ingresso.getSessao().getIngressos_vendidos())
                        + "|" + String.format("%5d", ingresso.getSessao().getSala().getQtdAssentos() - ingresso.getSessao().getIngressos_vendidos())
                        + "|"
                );
                linhaSeparadora();
            }
        }
    }
    
    private void linhaSeparadora(){
            System.out.println(String.format("%-7s", "+" + String.join("", Collections.nCopies(6, "-")))
                    + String.format("%-12s", "+" + String.join("", Collections.nCopies(11, "-")))
                    + String.format("%-32s", "+" + String.join("", Collections.nCopies(31, "-")))
                    + String.format("%-8s", "+" + String.join("", Collections.nCopies(8, "-")))
                    + String.format("%-5s", "+" + String.join("", Collections.nCopies(5, "-"))) 
                    + "+"
                    );
    }
}
