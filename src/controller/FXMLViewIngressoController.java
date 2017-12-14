/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.ActiveEvent;
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
import model.Ingresso;
import negocio.IngressoNegocio;
import util.CinemaConsts;

/**
 * FXML Controller class
 *
 * @author Roger
 */
public class FXMLViewIngressoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private VBox vboxIngresso;
    @FXML
    private TableView<Ingresso> tableViewIngresso;
    @FXML
    private TableColumn<Ingresso, String> tableColumnQtdIngresso;
    @FXML
    private TableColumn<Ingresso, String> tableColumnSessao;
    @FXML
    private TableColumn<Ingresso, String> tableColumnSala;
    @FXML
    private TableColumn<Ingresso, String> tableColumnFilme;

    @FXML
    private Button btnVender;
    @FXML
    private Button btnRemover;
    @FXML
    private Button btnVoltar;

    private IngressoNegocio ingressoNegocio;
    private List<Ingresso> listaIngressos;
    private ObservableList<Ingresso> observableListaIngressos;

    @FXML
    private void trataBotoes(ActionEvent event) {
        String title = "";
        Parent root = null;
        try {
            if (event.getSource().equals(btnVender)) {
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/FXMLIngresso.fxml"));
                root = (Parent) loader.load();
            } else if (event.getSource().equals(btnRemover)) {
                Ingresso ingresso = tableViewIngresso.getSelectionModel().getSelectedItem();
                if (ingresso != null){
                    ingressoNegocio.deletar(ingresso);
                }
            } else if (event.getSource().equals(btnVoltar)) {
                Stage stage = (Stage) vboxIngresso.getScene().getWindow();
                stage.close();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        if (root != null) {
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(vboxIngresso.getScene().getWindow());
            stage.setTitle(CinemaConsts.TITLE_FORM_INGRESSO);
            stage.showAndWait();
        }
        carregarTableViewIngresso();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ingressoNegocio = new IngressoNegocio();

        if (tableViewIngresso != null) {
            carregarTableViewIngresso();
        }
    }

    public void carregarTableViewIngresso() {
        tableColumnQtdIngresso.setCellValueFactory(new PropertyValueFactory<>("qtdIngresso"));
        tableColumnSessao.setCellValueFactory(new PropertyValueFactory<>("sessaoHora"));
        tableColumnSala.setCellValueFactory(new PropertyValueFactory<>("nomeSala"));
        tableColumnFilme.setCellValueFactory(new PropertyValueFactory<>("nomeFilme"));

        listaIngressos = ingressoNegocio.listaTableView();
        observableListaIngressos = FXCollections.observableArrayList(listaIngressos);
        tableViewIngresso.setItems(observableListaIngressos);
    }
}
