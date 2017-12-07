package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.CinemaConsts;

/**
 *
 * @author Roger
 */
public class FXMLMainController implements Initializable {

    @FXML
    AnchorPane painelPrincipal;
    @FXML
    private Button btnFilme;
    @FXML
    private Button btnSala;
    @FXML
    private Button btnSessao;
    @FXML
    private Button btnIngresso;
    @FXML
    private Button btnSair;
    @FXML
    private Label label;

    public String retornaEmail(){
        return "xx@xx.com.br";
    }    
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        String title = "";
        Parent painelProximaTela = null;
        if (event.getSource().equals(btnFilme)) {
            painelProximaTela = FXMLLoader.load(this.getClass().getResource("/view/FXMLMenuFilme.fxml"));
            title = CinemaConsts.TITLE_MNU_FILME;
        } else if (event.getSource().equals(btnSair)) {
            System.exit(0);
        }

        Stage janela = (Stage) painelPrincipal.getScene().getWindow();
        janela.setTitle(title);
        janela.setScene(new Scene(painelProximaTela));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
