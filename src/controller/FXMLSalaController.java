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
import util.ValidaDataException;

/**
 * FXML Controller class
 *
 * @author Roger
 */
public class FXMLSalaController implements Initializable {

    @FXML
    AnchorPane painelSala;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnVoltar;
    @FXML 
    private TextField textCodigo;
    @FXML
    private TextField textNrAssentos;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws ValidaDataException, IOException {
        if (event.getSource().equals(btnSalvar)) {
            
        } else if (event.getSource().equals(btnVoltar)) {
            Parent painelProximaTela = FXMLLoader.load(getClass().getResource("/view/FXMLMenuSala.fxml"));
            Stage janela = (Stage) painelSala.getScene().getWindow();
            janela.setScene(new Scene(painelProximaTela));
            
        }
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
