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
import model.Filme;
import negocio.FilmeNegocio;
import util.Console;
import view.UIUtil;

/**
 * FXML Controller class
 *
 * @author Roger
 */
public class FXMLListaFilmeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane painelListaFilme;
    @FXML
    private TextArea textAreaFilme;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<Filme> lstFilmes = new FilmeNegocio().listar();
        if (!lstFilmes.isEmpty()) {
            StringBuilder sbFilmes = new StringBuilder();
            sbFilmes.append(String.format("%-7s", "Código")
                    + String.format("%-31s", "|FILME")
                    + String.format("%-31s", "|GÊNERO")
                    + String.format("%-71s", "|SINÓPSIA") + "\n");

            for (Filme filme : lstFilmes) {
                sbFilmes.append(this.linhaSeparadora());

                sbFilmes.append(String.format("%-7s", filme.getId())
                        + String.format("%-31s", "|" + Console.formatString(filme.getNome(), 30))
                        + String.format("%-31s", "|" + Console.formatString(filme.getGenero(), 30))
                        + String.format("%-71s", "|" + Console.formatString(filme.getSinopsia(), 69)) + "\n");
            }
            sbFilmes.append(this.linhaSeparadora());
            this.textAreaFilme.setText(sbFilmes.toString());
            this.textAreaFilme.setFont(Font.font("Courier New", 11));
            this.textAreaFilme.setEditable(false);
        } else {
            UIUtil.exibeErro("Não há dados para exibir", JOptionPane.INFORMATION_MESSAGE);
            Stage stage = (Stage) painelListaFilme.getScene().getWindow();
            stage.close();
        }
    }

    private String linhaSeparadora() {
        return (String.format("%-7s", String.join("", Collections.nCopies(7, "-")))
                + String.format("%-31s", "+" + String.join("", Collections.nCopies(29, "-")))
                + String.format("%-31s", "+" + String.join("", Collections.nCopies(29, "-")))
                + String.format("%-71s", "+" + String.join("", Collections.nCopies(69, "-"))) + "\n");
    }

}
