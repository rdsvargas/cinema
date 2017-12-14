/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.Ingresso;
import negocio.IngressoNegocio;
import view.UIUtil;

/**
 * FXML Controller class
 *
 * @author Roger
 */
public class FXMLListaIngressoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane painelListaIngresso;
    @FXML
    private TextArea textAreaIngresso;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<Ingresso> lstIngressos = new IngressoNegocio().listar();
        if (!lstIngressos.isEmpty()) {
            StringBuilder sbIngressos = new StringBuilder();
            sbIngressos.append(String.format("%-7s", "|Hora")
                    + String.format("%-12s", "|Sala")
                    + String.format("%-32s", "|Filme")
                    + String.format("%-8s", "|Vendidos")
                    + String.format("%-5s", "|Saldo|") + "\n");
            sbIngressos.append(this.linhaSeparadora());
            for (Ingresso ingresso : lstIngressos) {
                sbIngressos.append(String.format("%-7s", "|" + ingresso.getSessao().getHora())
                        + String.format("%-12s", "|" + ingresso.getSessao().getSala().getCodigo())
                        + String.format("%-32s", "|" + ingresso.getSessao().getFilme().getNome())
                        + "|" + String.format("%8d", ingresso.getSessao().getIngressos_vendidos())
                        + "|" + String.format("%5d", ingresso.getSessao().getSala().getQtdAssentos() - ingresso.getSessao().getIngressos_vendidos())
                        + "|\n");
                sbIngressos.append(this.linhaSeparadora());
            }
            this.textAreaIngresso.setText(sbIngressos.toString());
            this.textAreaIngresso.setFont(Font.font("Courier New", 11));
            this.textAreaIngresso.setEditable(false);
        } else {
            UIUtil.exibeErro("Não há dados para exibir", JOptionPane.INFORMATION_MESSAGE);
            Stage stage = (Stage) painelListaIngresso.getScene().getWindow();
            stage.close();
        }
    }

    private String linhaSeparadora() {
        return String.format("%-7s", "+" + String.join("", Collections.nCopies(6, "-")))
                + String.format("%-12s", "+" + String.join("", Collections.nCopies(11, "-")))
                + String.format("%-32s", "+" + String.join("", Collections.nCopies(31, "-")))
                + String.format("%-8s", "+" + String.join("", Collections.nCopies(8, "-")))
                + String.format("%-5s", "+" + String.join("", Collections.nCopies(5, "-")))
                + "+\n";
    }

}
