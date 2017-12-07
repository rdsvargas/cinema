/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import model.Filme;
import negocio.FilmeNegocio;

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
    TextArea textAreaFilme;

    private FilmeNegocio filmeNegocio;

    public FXMLListaFilmeController() {
        this.filmeNegocio = new FilmeNegocio();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        StringBuilder sbFilmes = new StringBuilder();
        sbFilmes.append(String.format("%-10s", "Código")
                + String.format("%-31s", "|FILME")
                + String.format("%-21s", "|GÊNERO")
                + String.format("%-51s", "|SINÓPSIA")+"\n");

            for (Filme filme : filmeNegocio.listar()) {
                sbFilmes.append(this.linhaSeparadora());

                sbFilmes.append(String.format("%-10s", filme.getId())
                        + String.format("%-31s", "|" + filme.getNome())
                        + String.format("%-21s", "|" + filme.getGenero())
                        + String.format("%-51s", "|" + filme.getSinopsia()) +"\n");
            }
            sbFilmes.append(this.linhaSeparadora());
        this.textAreaFilme.setText(sbFilmes.toString());
        this.textAreaFilme.setFont(Font.font("Courier New", 11));
        this.textAreaFilme.setEditable(false);
                
    }

    private String linhaSeparadora() {
        return (String.format("%-10s", String.join("", Collections.nCopies(10, "-")))
                + String.format("%-31s", "+" + String.join("", Collections.nCopies(29, "-")))
                + String.format("%-21s", "+" + String.join("", Collections.nCopies(19, "-")))
                + String.format("%-51s", "+" + String.join("", Collections.nCopies(49, "-"))) + "\n");
    }

}
