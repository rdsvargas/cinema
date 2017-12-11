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
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import util.CinemaConsts;

/**
 *
 * @author Roger
 */
public class FXMLMainController implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    AnchorPane painelPrincipal;
    
    // Menu Cadastro
    @FXML
    private MenuItem mnuCadastroFilme;
    @FXML
    private MenuItem mnuCadastroSala;
    @FXML
    private MenuItem mnuCadastroSessao;
    @FXML
    private MenuItem mnuIngressos;
    @FXML 
    private MenuItem mnuClose;
    
    // Menu Relatórios
    @FXML
    private MenuItem mnuLstFilmes;
    @FXML
    private MenuItem mnuLstSalas;
    @FXML
    private MenuItem mnuLstSessoes;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        String title = "";
        Parent root = null;
        if (event.getSource().equals(mnuCadastroFilme)) {
            root = FXMLLoader.load(this.getClass().getResource("/view/FXMLViewFilme.fxml"));
            title = CinemaConsts.TITLE_CADASTRO_FILME;
        } else if (event.getSource().equals(mnuCadastroSala)){
            root = FXMLLoader.load(this.getClass().getResource("/view/FXMLViewSala.fxml"));
            title = CinemaConsts.TITLE_CADASTRO_SALA;
        } else if (event.getSource().equals(mnuCadastroSessao)){
        } else if (event.getSource().equals(mnuIngressos)){
        } else if (event.getSource().equals(mnuLstFilmes)){
            root = FXMLLoader.load(this.getClass().getResource("/view/FXMLListaFilme.fxml"));
            title = CinemaConsts.TITLE_LISTA_FILME;
        } else if (event.getSource().equals(mnuLstSalas)){
        } else if (event.getSource().equals(mnuLstSessoes)){
        } else if (event.getSource().equals(mnuClose)) {
            System.exit(0);
        }

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.initOwner(vbox.getScene().getWindow());
        stage.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
