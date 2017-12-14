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
import model.Sala;
import negocio.SalaNegocio;
import view.UIUtil;

/**
 * FXML Controller class
 *
 * @author Roger
 */
public class FXMLListaSalaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane painelListaSala;
    @FXML
    private TextArea textAreaSala;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<Sala> lstSalas = new SalaNegocio().listar();
        if (!lstSalas.isEmpty()) {
            StringBuilder sbSalas = new StringBuilder();
            sbSalas.append(String.format("%-10s", "Sala")
                    + String.format("%-11s", "|Código")
                    + String.format("%-12s", "|Qtd Assentos\n"));
            for (Sala sala : lstSalas){
                sbSalas.append(this.linhaSeparadora());
                sbSalas.append(String.format("%-10s", sala.getId())
                        + String.format("%-11s", "|" + sala.getCodigo())
                        + String.format("%-12s", "|" + sala.getQtdAssentos()) + "\n");
            }
            sbSalas.append(this.linhaSeparadora());
            this.textAreaSala.setText(sbSalas.toString());
            this.textAreaSala.setFont(Font.font("Courier New", 11));
            this.textAreaSala.setEditable(false);
        } else {
            UIUtil.exibeErro("Não há dados para exibir", JOptionPane.INFORMATION_MESSAGE);
            Stage stage = (Stage) painelListaSala.getScene().getWindow();
            stage.close();
        }
    }

    private String linhaSeparadora(){
        return String.format("%-10s", String.join("", Collections.nCopies(10, "-")))
                + String.format("%-11s", "+" + String.join("", Collections.nCopies(10, "-")))
                + String.format("%-12s", "+" + String.join("", Collections.nCopies(12, "-")) + "\n");
    }
}
