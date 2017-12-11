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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Filme;
import negocio.FilmeNegocio;
import util.ValidaDataException;

/**
 *
 * @author Roger
 */
public class FXMLFilmeController implements Initializable {

    @FXML
    private AnchorPane painelFilme;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnVoltar;
    @FXML
    private TextField textFilme;
    @FXML
    private TextField textGenero;
    @FXML
    private TextField textSinopsia;

    private FilmeNegocio filmeNegocio;
    private Filme filmeSel;

    @FXML
    private void handleButtonAction(ActionEvent event) throws ValidaDataException, IOException {
        Stage stage = (Stage) painelFilme.getScene().getWindow();

        if (event.getSource().equals(btnSalvar)) {
            filmeNegocio = new FilmeNegocio();
            if (this.filmeSel == null) {
                filmeNegocio.salvar(new Filme(textFilme.getText(),
                        textGenero.getText(),
                        textSinopsia.getText()));
            } else{
                filmeSel.setNome(textFilme.getText());
                filmeSel.setGenero(textGenero.getText());
                filmeSel.setSinopsia(textSinopsia.getText());
                filmeNegocio.atualizar(filmeSel);
            }
        }
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setFilmeSelecionado(Filme filmeSel) {
        if (filmeSel != null) {
            this.filmeSel = filmeSel;
            textFilme.setText(this.filmeSel.getNome());
            textGenero.setText(this.filmeSel.getGenero());
            textSinopsia.setText(this.filmeSel.getSinopsia());
        }
    }

}
