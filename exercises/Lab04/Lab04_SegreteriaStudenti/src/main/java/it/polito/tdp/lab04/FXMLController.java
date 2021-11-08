/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class FXMLController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="choiceCorso"
    private ComboBox<Corso> choiceCorso; // Value injected by FXMLLoader

    @FXML // fx:id="btnIscrittiCorso"
    private Button btnIscrittiCorso; // Value injected by FXMLLoader

    @FXML // fx:id="txtMatricola"
    private TextField txtMatricola; // Value injected by FXMLLoader

    @FXML // fx:id="btnAutoCompletamento"
    private Button btnAutoCompletamento; // Value injected by FXMLLoader

    @FXML // fx:id="txtNome"
    private TextField txtNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtCognome"
    private TextField txtCognome; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaCorsi"
    private Button btnCercaCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="btnIscrivi"
    private Button btnIscrivi; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML // fx:id="btnReset"
    private Button btnReset; // Value injected by FXMLLoader

    private Model model;

    private ObservableList<Corso> scelte;

    @FXML
    void autoCompletamento(ActionEvent event) {

        Integer matricola;
        try {
            matricola = Integer.parseInt(this.txtMatricola.getText());
        } catch (NumberFormatException e) {
            this.txtResult.appendText("La matricola deve essere un numero\n");
            return;
        }

        Studente stud = this.model.getStudenteDaMatricola(matricola);

        try {
            this.txtNome.setText(stud.getNome());
            this.txtCognome.setText(stud.getCognome());
        } catch (NullPointerException e) {
            this.txtResult.appendText("Studente non trovato\n");
        }

    }

    @FXML
    void cercaCorsi(ActionEvent event) {

        Integer matricola;
        try {
            matricola = Integer.parseInt(this.txtMatricola.getText());
        } catch (NumberFormatException e) {
            this.txtResult.appendText("La matricola deve essere un numero\n");
            return;
        }

        Studente stud = this.model.getStudenteDaMatricola(matricola);
        if (stud == null) {
            this.txtResult.appendText("Studente non trovato\n");
            return;
        }

        List<Corso> corsi = this.model.getCorsiStudente(stud);
        if (corsi.isEmpty()) {
            this.txtResult.appendText("Lo studente non è iscritto a nessun corso\n");
            return;
        }

        for (Corso c : corsi) {
            this.txtResult.appendText(c + "\n");
        }

    }

    @FXML
    void cercaIscrittiCorso(ActionEvent event) {

        if (this.choiceCorso.getValue() == null) {
            this.txtResult.appendText("Selezionare un corso\n");
            return;
        }

        List<Studente> iscritti = this.model
                .getStudentiIscrittiAlCorso(this.choiceCorso.getValue());

        if (iscritti.isEmpty()) {
            this.txtResult.appendText("Il corso non ha iscritti\n");
        } else {
            for (Studente s : iscritti) {
                this.txtResult.appendText(s + "\n");
            }
        }

    }

    @FXML
    void iscrivi(ActionEvent event) {

        Integer matricola;
        try {
            matricola = Integer.parseInt(this.txtMatricola.getText());
        } catch (NumberFormatException e) {
            this.txtResult.appendText("La matricola deve essere un numero\n");
            return;
        }

        Studente stud = this.model.getStudenteDaMatricola(matricola);
        if (stud == null) {
            this.txtResult.appendText("Studente non trovato\n");
            return;
        }

        Corso corso = this.choiceCorso.getValue();
        if (corso == null) {
            this.txtResult.appendText("Selezionare un corso\n");
            return;
        }

        if (this.model.isStudenteIscrittoACorso(stud, corso)) {
            this.txtResult.appendText("Studente già iscritto al corso\n");
        } else {
            if (this.model.iscriviStudenteACorso(stud, corso)) {
                this.txtResult.appendText("Studente iscritto al corso correttamente\n");
            } else {
                this.txtResult.appendText("Studente non iscritto, errore\n");
            }

        }

    }

    @FXML
    void popola(MouseEvent event) {
        if (this.scelte != null) {
            return;
        }
        List<Corso> corsi = new ArrayList<>();
        corsi.add(null);
        corsi.addAll(this.model.getListaCorsi());
        this.scelte = FXCollections.observableList(corsi);
        this.choiceCorso.setItems(scelte);
    }

    @FXML
    void reset(ActionEvent event) {
        this.txtNome.clear();
        this.txtCognome.clear();
        this.txtMatricola.clear();
        this.txtResult.clear();
        this.choiceCorso.setValue(null);

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert choiceCorso != null : "fx:id=\"choiceCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrittiCorso != null : "fx:id=\"btnIscrittiCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAutoCompletamento != null : "fx:id=\"btnAutoCompletamento\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";

    }

    public void setModel(Model model) {
        this.model = model;
    }
}
