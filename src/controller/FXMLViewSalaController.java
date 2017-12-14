/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.Sala;
import negocio.SalaNegocio;
import util.CinemaConsts;

/**
 * FXML Controller class
 *
 * @author Roger
 */
public class FXMLViewSalaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private VBox vboxSala;
    
    @FXML
    private TableView<Sala> tableViewSalas;
    @FXML
    private TableColumn<Sala, String> tableColumnSala;
    @FXML
    private TableColumn<Sala, String> tableColumnNrAssentos;

    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnRemover;
    @FXML
    private Button btnVoltar;

    private SalaNegocio salaNegocio;
    private List<Sala> listaSalas;
    private ObservableList<Sala> observableListaSalas;

    @FXML
    private void trataBotoes(ActionEvent event) {
        String title = "";
        Parent root = null;
        try {
            if (event.getSource().equals(btnCadastrar)) {
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/FXMLSala.fxml"));
                root = (Parent) loader.load();
                FXMLSalaController controller = (FXMLSalaController) loader.getController();
                controller.setSalaSelecionada(null);
            } else if (event.getSource().equals(btnEditar)) {
                Sala salaSel = tableViewSalas.getSelectionModel().getSelectedItem();
                if (salaSel != null){
                    FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/FXMLSala.fxml"));
                    root = (Parent) loader.load();
                    FXMLSalaController controller = (FXMLSalaController) loader.getController();
                    controller.setSalaSelecionada(salaSel);
                }
            } else if (event.getSource().equals(btnRemover)) {
                Sala salaSel = tableViewSalas.getSelectionModel().getSelectedItem();
                if (salaSel != null){
                    salaNegocio.deletar(salaSel);
                }
            } else if (event.getSource().equals(btnVoltar)) {
                Stage stage = (Stage) vboxSala.getScene().getWindow();
                stage.close();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        if (root != null) {
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(CinemaConsts.TITLE_FORM_SALA);
            stage.initOwner(vboxSala.getScene().getWindow());
            stage.showAndWait();
        }
        carregarTableViewSalas();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        salaNegocio = new SalaNegocio();

        if (tableViewSalas != null){
            carregarTableViewSalas();
        }
    }

    private void carregarTableViewSalas() {
        tableColumnSala.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tableColumnNrAssentos.setCellValueFactory(new PropertyValueFactory<>("qtdAssentos"));
        
        listaSalas = salaNegocio.listar();
        observableListaSalas = FXCollections.observableArrayList(listaSalas);
        tableViewSalas.setItems(observableListaSalas);
    }
}
