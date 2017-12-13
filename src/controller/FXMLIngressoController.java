/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.Filme;
import model.Ingresso;
import model.Sessao;
import negocio.FilmeNegocio;
import negocio.IngressoNegocio;
import negocio.SessaoNegocio;

/**
 * FXML Controller class
 *
 * @author Roger
 */
public class FXMLIngressoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane painelIngresso;
    @FXML
    private ComboBox<Filme> cbFilme;
    @FXML
    private ComboBox<Sessao> cbSessao;
    @FXML
    private TextField textFieldQtdIngressos;
    @FXML
    private Label lblSaldo;
    @FXML
    private Button btnSalvar;

    private SessaoNegocio sessaoNegocio;
    private FilmeNegocio filmeNegocio;
    private IngressoNegocio ingressoNegocio;

    @FXML
    private void trataBotoes(ActionEvent event) {
        Stage stage = (Stage) painelIngresso.getScene().getWindow();
        boolean exitForm = true;
        if (event.getSource().equals(btnSalvar)) {
            try {
                Sessao sessao = cbSessao.getSelectionModel().getSelectedItem();
                int qtdIngressos = Integer.parseInt(textFieldQtdIngressos.getText());
                int saldo = sessao.getSala().getQtdAssentos() - sessao.getIngressos_vendidos();
                
                if (qtdIngressos == 0){
                    throw new NumberFormatException();
                }
                if ( qtdIngressos > saldo){
                    throw new Exception("Saldo insuficiente.");
                }
                
                Ingresso ingresso = new Ingresso(sessao, qtdIngressos);
                ingressoNegocio.salvar(ingresso, ingresso.getQtdIngresso());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Quantidade de ingressos informada inválida.", "Alerta", JOptionPane.INFORMATION_MESSAGE);
                textFieldQtdIngressos.requestFocus();
                exitForm = false;
            } catch (Exception ex){
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Alerta", JOptionPane.INFORMATION_MESSAGE);
                textFieldQtdIngressos.requestFocus();
                exitForm = false;
            }
        }

        if (exitForm) {
            stage.close();
        }
    }

    @FXML
    private void cbFilmeOnAction(ActionEvent event) {
        Filme filme = cbFilme.getSelectionModel().getSelectedItem();
        fillComboboxSessao(filme.getId());
    }

    @FXML
    private void cbSessaoOnAction(ActionEvent event) {
        Sessao sessao = cbSessao.getSelectionModel().getSelectedItem();
        int saldo = sessao.getSala().getQtdAssentos() - sessao.getIngressos_vendidos();
        lblSaldo.setText("Saldo: " + String.valueOf(saldo));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        filmeNegocio = new FilmeNegocio();
        sessaoNegocio = new SessaoNegocio();
        ingressoNegocio = new IngressoNegocio();
        fillComboBoxFilme();
    }

    private void fillComboBoxFilme() {
        cbFilme.getItems().clear();
        cbFilme.getItems().addAll(filmeNegocio.listaFilmeBySessao());
        cbFilme.getSelectionModel().select(0);
        cbFilmeOnAction(null);
    }

    private void fillComboboxSessao(int filme_id) {
        cbSessao.getItems().clear();
        cbSessao.getItems().addAll(sessaoNegocio.listaSessaoByFilme(filme_id));
        cbSessao.getSelectionModel().select(0);
        cbSessaoOnAction(null);
    }
}
