/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Sessao;
import negocio.FilmeNegocio;
import negocio.SalaNegocio;
import negocio.SessaoNegocio;
import util.Console;

/**
 * FXML Controller class
 *
 * @author Roger
 */
public class FXMLSessaoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane painelSessao;
    @FXML
    private ComboBox cbFilme;
    @FXML
    private ComboBox cbSala;
    @FXML
    private Button btnSalvar;

    private SessaoNegocio sessaoNegocio;
    private FilmeNegocio filmeNegocio;
    private SalaNegocio salaNegocio;
    private Sessao sessaoSel;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        Stage stage = (Stage) painelSessao.getScene().getWindow();
        if (event.getSource().equals(btnSalvar)) {
            sessaoNegocio = new SessaoNegocio();
            if (this.sessaoSel == null) {

            }
        }
        stage.close();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        filmeNegocio = new FilmeNegocio();
        salaNegocio = new SalaNegocio();
        fillComboBoxFilme();
        fillComboBoxSala();
    }

    public void setSessaoSelecionada(Sessao sessaoSel) {
        if (sessaoSel != null) {
            this.sessaoSel = sessaoSel;
        }
    }

    private void fillComboBoxFilme() {
        cbFilme.getItems().clear();
        cbFilme.getItems().addAll(filmeNegocio.listaFilme());
        if (this.sessaoSel == null) {
            cbFilme.getSelectionModel().select(0);
        } else {
            cbFilme.setValue(Console.formatString(this.sessaoSel.getFilme().getId(), 5) + " | " + this.sessaoSel.getFilme().getNome());
        }
    }

    private void fillComboBoxSala() {
        cbSala.getItems().clear();
        cbSala.getItems().addAll(salaNegocio.listaSala());

    }
}
