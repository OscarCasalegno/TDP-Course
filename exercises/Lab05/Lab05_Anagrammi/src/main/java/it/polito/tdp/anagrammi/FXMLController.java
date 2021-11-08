package it.polito.tdp.anagrammi;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.anagrammi.model.Anagramma;
import it.polito.tdp.anagrammi.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtInput;

    @FXML
    private Button btnCalcola;

    @FXML
    private TextArea txtCorretti;

    @FXML
    private TextArea txtSbagliati;

    @FXML
    private Button btnReset;

    private Model model;

    @FXML
    void calcola(ActionEvent event) {
        this.txtCorretti.clear();
        this.txtSbagliati.clear();

        String parola = this.txtInput.getText();

        List<Anagramma> anagrammi = this.model.calcolaAnagrammi(parola);

        for (Anagramma a : anagrammi) {
            if (a.isCorretto()) {
                this.txtCorretti.appendText(a.toString() + "\n");
            } else {
                this.txtSbagliati.appendText(a.toString() + "\n");
            }
        }

    }

    @FXML
    void reset(ActionEvent event) {

        this.txtInput.clear();
        this.txtCorretti.clear();
        this.txtSbagliati.clear();

    }

    @FXML
    void initialize() {
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCalcola != null : "fx:id=\"btnCalcola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCorretti != null : "fx:id=\"txtCorretti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtSbagliati != null : "fx:id=\"txtSbagliati\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";

    }

    public void setModel(Model model) {
        this.model = model;

    }

}
