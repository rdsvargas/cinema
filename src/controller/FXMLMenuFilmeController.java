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
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.CinemaConsts;

/**
 * FXML Controller class
 *
 * @author Roger
 */
public class FXMLMenuFilmeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    AnchorPane painelMenuFilme;
    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnAlterar;
    @FXML
    private Button btnListar;
    @FXML
    private Button btnVoltar;

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        String title = "";
        Parent painelProximaTela = null;
        if (event.getSource().equals(btnCadastrar)){
            painelProximaTela = FXMLLoader.load(getClass().getResource("/view/FXMLFilme.fxml"));
            title = CinemaConsts.TITLE_CADASTRO_FILME;
            
        } else if (event.getSource().equals(btnVoltar)) {
            painelProximaTela = FXMLLoader.load(getClass().getResource("/view/FXMLMain.fxml"));
            title = CinemaConsts.TITLE_MAIN;
        }
        Stage janela = (Stage) painelMenuFilme.getScene().getWindow();
        janela.setTitle(title);
        janela.setScene(new Scene(painelProximaTela));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
