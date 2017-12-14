/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
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
import model.Filme;
import negocio.FilmeNegocio;
import util.ValidaDataException;
import util.CinemaConsts;

/**
 *
 * @author Roger
 */
public class FXMLViewFilmeController implements Initializable {

    @FXML
    private VBox vboxFilme;

    @FXML
    private TableView<Filme> tableViewFilmes;
    @FXML
    private TableColumn<Filme, String> tableColumnFilme;
    @FXML
    private TableColumn<Filme, String> tableColumnGenero;
    @FXML
    private TableColumn<Filme, String> tableColumnSinopsia;

    @FXML
    private Button btnCadastrar;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnRemover;
    @FXML
    private Button btnVoltar;

    private FilmeNegocio filmeNegocio;
    private List<Filme> listaFilmes;
    private ObservableList<Filme> observableListaFilmes;

    @FXML
    private void trataBotoes(ActionEvent event) throws ValidaDataException, IOException {
        Parent root = null;
        try {
            if (event.getSource().equals(btnCadastrar)) {
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/FXMLFilme.fxml"));
                root = (Parent) loader.load();
                FXMLFilmeController controller = (FXMLFilmeController) loader.getController();
                controller.setFilmeSelecionado(null);
            } else if (event.getSource().equals(btnEditar)) {
                Filme filmeSel = tableViewFilmes.getSelectionModel().getSelectedItem();
                if (filmeSel != null) {
                    FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/FXMLFilme.fxml"));
                    root = (Parent) loader.load();
                    FXMLFilmeController controller = (FXMLFilmeController) loader.getController();
                    controller.setFilmeSelecionado(filmeSel);
                }
            } else if (event.getSource().equals(btnRemover)) {
                Filme filmeSel = tableViewFilmes.getSelectionModel().getSelectedItem();
                if (filmeSel != null) {
                    filmeNegocio.deletar(filmeSel);
                }
            } else if (event.getSource().equals(btnVoltar)) {
                Stage stage = (Stage) vboxFilme.getScene().getWindow();
                stage.close();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        if (root != null) {
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(vboxFilme.getScene().getWindow());
            stage.setTitle(CinemaConsts.TITLE_FORM_FILME);
            stage.showAndWait();
        }
        carregarTableViewFilmes();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        filmeNegocio = new FilmeNegocio();

        if (tableViewFilmes != null) {
            carregarTableViewFilmes();
        }
    }

    private void carregarTableViewFilmes() {
        tableColumnFilme.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tableColumnGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        tableColumnSinopsia.setCellValueFactory(new PropertyValueFactory<>("sinopsia"));

        listaFilmes = filmeNegocio.listar();
        observableListaFilmes = FXCollections.observableArrayList(listaFilmes);
        tableViewFilmes.setItems(observableListaFilmes);
    }
}
