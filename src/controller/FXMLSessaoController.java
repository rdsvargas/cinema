/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.Filme;
import model.Sala;
import model.Sessao;
import negocio.FilmeNegocio;
import negocio.SalaNegocio;
import negocio.SessaoNegocio;
import util.Console;
import util.DateUtil;

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
    private TextField textFieldSessao;
    @FXML
    private ComboBox<Filme> cbFilme;
    @FXML
    private ComboBox<Sala> cbSala;
    @FXML
    private Button btnSalvar;
    
    private SessaoNegocio sessaoNegocio;
    private FilmeNegocio filmeNegocio;
    private SalaNegocio salaNegocio;
    private Sessao sessaoSel;

    @FXML
    private void handleButtonAction(ActionEvent event) throws Exception {
        Stage stage = (Stage) painelSessao.getScene().getWindow();
        boolean exitForm = true;
        if (event.getSource().equals(btnSalvar)) {
            try {
                LocalTime horaSessao = DateUtil.stringToTime(textFieldSessao.getText());

                sessaoNegocio = new SessaoNegocio();
                Filme filme = cbFilme.getSelectionModel().getSelectedItem();
                Sala sala = cbSala.getSelectionModel().getSelectedItem();
                if (this.sessaoSel == null) {
                    sessaoNegocio.salvar(new Sessao(
                            horaSessao,
                            sala,
                            filme));
                } else {
                    this.sessaoSel.setHora(DateUtil.stringToTime(textFieldSessao.getText()));
                    this.sessaoSel.setFilme(filme);
                    this.sessaoSel.setSala(sala);
                    sessaoNegocio.atualizar(this.sessaoSel);
                }
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(null, "Informar formato 24h ex: 13:00", "Alerta", JOptionPane.INFORMATION_MESSAGE);
                textFieldSessao.requestFocus();
                exitForm = false;
            } catch (Exception ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Alerta", JOptionPane.INFORMATION_MESSAGE);
                textFieldSessao.requestFocus();
                exitForm = false;
            }
        }
        if (exitForm) {
            stage.close();
        }
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
            textFieldSessao.setText(DateUtil.timeToString(this.sessaoSel.getHora()));
            cbFilme.setValue(sessaoSel.getFilme());
            cbSala.setValue(sessaoSel.getSala());
        }
    }

    private void fillComboBoxFilme() {
        cbFilme.getItems().clear();
        cbFilme.getItems().addAll(filmeNegocio.listar());
        cbFilme.getSelectionModel().select(0);
    }

    private void fillComboBoxSala() {
        cbSala.getItems().clear();
        cbSala.getItems().addAll(salaNegocio.listar());
        cbSala.getSelectionModel().select(0);
    }
}

