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
import model.Sala;
import negocio.FilmeNegocio;
import negocio.SalaNegocio;
import util.ValidaDataException;

/**
 * FXML Controller class
 *
 * @author Roger
 */
public class FXMLSalaController implements Initializable {

    @FXML
    private AnchorPane painelSala;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnVoltar;
    @FXML 
    private TextField textCodigo;
    @FXML
    private TextField textNrAssentos;

    private SalaNegocio salaNegocio;
    private Sala salaSel;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws ValidaDataException, IOException {
        Stage stage = (Stage) painelSala.getScene().getWindow();
        if (event.getSource().equals(btnSalvar)) {
            salaNegocio = new SalaNegocio();
            if (this.salaSel == null){
                salaNegocio.salvar(new Sala(textCodigo.getText(), 
                        Integer.parseInt(textNrAssentos.getText())));
            } else {
                salaSel.setCodigo(textCodigo.getText());
                salaSel.setQtdAssentos(Integer.parseInt(textNrAssentos.getText()));
                salaNegocio.atualizar(salaSel);
            }
        }
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setSalaSelecionada(Sala salaSel){
        if (salaSel != null){
            this.salaSel = salaSel;
            textCodigo.setText(this.salaSel.getCodigo());
            textNrAssentos.setText(Integer.toString(this.salaSel.getQtdAssentos()));
        }
    }
}
