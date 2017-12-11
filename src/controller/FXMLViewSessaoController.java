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
import model.Sessao;
import negocio.SessaoNegocio;

/**
 * FXML Controller class
 *
 * @author Roger
 */
public class FXMLViewSessaoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private VBox vboxSessao;
    @FXML
    private TableView<Sessao> tableViewSessao;
    @FXML
    private TableColumn<Sessao, String> tableColumnSessao;
    @FXML
    private TableColumn<Sessao, String> tableColumnFilme;
    @FXML
    private TableColumn<Sessao, String> tableColumnSala;

    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnRemover;
    @FXML
    private Button btnVoltar;

    private SessaoNegocio sessaoNegocio;
    private List<Sessao> listaSessoes;
    private ObservableList<Sessao> observableListaSessoes;

    @FXML
    private void trataBotoes(ActionEvent event) {
        String title = "";
        Parent root = null;
        try {
            if (event.getSource().equals(btnCadastrar)) {
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/FXMLSessao.fxml"));
                root = (Parent) loader.load();
                FXMLSessaoController controller = (FXMLSessaoController) loader.getController();
                controller.setSessaoSelecionada(null);
            } else if (event.getSource().equals(btnEditar)) {
            } else if (event.getSource().equals(btnRemover)) {
            } else if (event.getSource().equals(btnVoltar)) {
                Stage stage = (Stage) vboxSessao.getScene().getWindow();
                stage.close();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        if (root != null) {
            Stage dialogStage = new Stage();
            dialogStage.setScene(new Scene(root));
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.initOwner(vboxSessao.getScene().getWindow());
            dialogStage.showAndWait();
        }
        carregarTableViewSessoes();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        sessaoNegocio = new SessaoNegocio();
        
        if (tableViewSessao != null){
            carregarTableViewSessoes();
        }
    }

    private void carregarTableViewSessoes() {
        tableColumnSessao.setCellValueFactory(new PropertyValueFactory<>("hora"));
        tableColumnFilme.setCellValueFactory(new PropertyValueFactory<>("nomeFilme"));
        tableColumnSala.setCellValueFactory(new PropertyValueFactory<>("nomeSala"));
        
        listaSessoes = sessaoNegocio.listar();
        observableListaSessoes = FXCollections.observableArrayList(listaSessoes);
        tableViewSessao.setItems(observableListaSessoes);
    }
    
    
}
