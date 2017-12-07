/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import negocio.FilmeNegocio;
import util.ValidaDataException;

/**
 *
 * @author Roger
 */
public class FXMLFilmeController implements Initializable {

    @FXML
    ComboBox<String> cbFilme;
    @FXML
    AnchorPane painelFilme;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnVoltar;
    @FXML
    private TextField textFilme;
    @FXML
    private TextField textGenero;
    @FXML
    private TextField textSinopsia;

    private FilmeNegocio filmeNegocio;

    private int filme_id;

    private void fillComboBoxFilme() {
        cbFilme.getItems().clear();
        cbFilme.getItems().addAll(filmeNegocio.listaFilme());
        cbFilme.setValue("Novo");

        filme_id = 0;
    }

    public FXMLFilmeController() {
        filmeNegocio = new FilmeNegocio();
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws ValidaDataException, IOException {
        if (event.getSource().equals(btnSalvar)) {
            model.Filme filme;
            if (this.filme_id == 0) {
                filme = new model.Filme(textFilme.getText(), textGenero.getText(), textSinopsia.getText());
                filmeNegocio.salvar(filme);
            } else {
                filme = new model.Filme(filme_id, textFilme.getText(), textGenero.getText(), textSinopsia.getText());
                filmeNegocio.atualizar(filme);
            }

            JOptionPane.showMessageDialog(null, filme.toString() + " -> cadastrado com sucesso!");
            this.fillComboBoxFilme();
            textFilme.clear();
            textGenero.clear();
            textSinopsia.clear();
        } else if (event.getSource().equals(btnVoltar)) {
            Parent painelProximaTela = FXMLLoader.load(getClass().getResource("/view/FXMLMenuFilme.fxml"));
            Stage janela = (Stage) painelFilme.getScene().getWindow();
            janela.setScene(new Scene(painelProximaTela));
        }
    }

    @FXML
    private void handleComboBox(ActionEvent event) {
        if (!cbFilme.getItems().isEmpty()) {
            String filmeSel = cbFilme.getSelectionModel().getSelectedItem();
            if (filmeSel.equals("Novo")) {
                filme_id = 0;
            } else {
                filme_id = Integer.parseInt(filmeSel.substring(0, 5).trim());
                model.Filme filme = filmeNegocio.localizarPorId(filme_id);
                textFilme.setText(filme.getNome());
                textGenero.setText(filme.getGenero());
                textSinopsia.setText(filme.getSinopsia());
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.fillComboBoxFilme();
    }

}
