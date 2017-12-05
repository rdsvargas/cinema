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

    public FXMLFilmeController() {
        filmeNegocio = new FilmeNegocio();
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws ValidaDataException, IOException {
        if (event.getSource().equals(btnSalvar)) {
            model.Filme filme = new model.Filme(textFilme.getText(), textGenero.getText(), textSinopsia.getText());
            filmeNegocio.salvar(filme);
            JOptionPane.showMessageDialog(null, filme.toString() + " -> cadastrado com sucesso!");
            textFilme.clear();
            textGenero.clear();
            textSinopsia.clear();
        } else if (event.getSource().equals(btnVoltar)) {
            Parent painelProximaTela = FXMLLoader.load(getClass().getResource("/view/FXMLMenuFilme.fxml"));
            Stage janela = (Stage) painelFilme.getScene().getWindow();
            janela.setScene(new Scene(painelProximaTela));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
