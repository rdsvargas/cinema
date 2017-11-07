package view;

import dao.db.BDException;
import java.util.Collections;
import java.util.List;
import model.Sala;
import negocio.SalaNegocio;
import util.Console;
import util.ValidaDataException;
import view.menu.SalaMenu;

public class SalaUI {

    private SalaNegocio salaNegocio;

    public SalaUI() {
        this.salaNegocio = new SalaNegocio();
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
                case SalaMenu.OP_ALTERAR:
                    alterarSala();
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
        String codigo = Console.scanString("Identificação Sala: ");
        int qtdAssentos = Console.scanInt("Quantidade de Assentos: ");
        if (qtdAssentos == -1) {
            System.out.println("Informe um valor numérico inteiro positivo para a quantidade de assentos");
        } else {
            try {
                Sala sala = new Sala(codigo, qtdAssentos);
                salaNegocio.salvar(sala);
                System.out.println(sala.toString() + " -> cadastrada com sucesso!");
            } catch (ValidaDataException ex) {
                UIUtil.mostrarErro(ex.getMessage());
            }
        }
    }

    private void alterarSala() {
        String codigo = Console.scanString("Informe o código da Sala: ");
        try {
            Sala sala = salaNegocio.localizarPorCodigo(codigo);

            System.out.println(sala.toString() + "-> Sala");
            codigo = Console.scanString("Identificação Sala: ");
            int qtdAssentos = Console.scanInt("Quantidade de Assentos: ");
            if (qtdAssentos == -1) {
                System.out.println("Informe um valor numérico inteiro positivo para a quantidade de assentos");
            } else{
               sala.setCode(codigo);
               sala.setQtdAssentos(qtdAssentos);
               salaNegocio.atualizar(sala);
            }

        } catch (ValidaDataException | BDException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        }
    }

    public void listaSalas() {
        List<Sala> listaSalas = salaNegocio.listar();

        if (listaSalas.isEmpty()) {
            System.out.println("--------------------------------");
            System.out.println("Nao existem filmes cadastrados");
            System.out.println("--------------------------------\n");
        } else {
            System.out.println(String.format("%-10s", "Sala")
                    + String.format("%-11s", "|Código")
                    + String.format("%-12s", "|Qtd Assentos"));
            for (Sala sala : listaSalas) {
                System.out.println(linhaSeparadora());
                System.out.println(String.format("%-10s", sala.getId())
                        + String.format("%-11s", "|" + sala.getCodigo())
                        + String.format("%-12s", "|" + sala.getQtdAssentos()));
            }
            System.out.println(linhaSeparadora());
        }
    }

    private String linhaSeparadora() {
        return String.format("%-10s", String.join("", Collections.nCopies(10, "-")))
                + String.format("%-11s", "+" + String.join("", Collections.nCopies(10, "-")))
                + String.format("%-12s", "+" + String.join("", Collections.nCopies(12, "-")));

    }
}
