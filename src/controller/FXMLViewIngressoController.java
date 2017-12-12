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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import model.Ingresso;
import negocio.IngressoNegocio;

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

    private IngressoNegocio ingressoNegocio;
    private List<Ingresso> listaIngressos;
    private ObservableList<Ingresso> observableListaIngressos;

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
