package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> choiceLingua;

    @FXML
    private TextArea txtDaControllare;

    @FXML
    private Button btnSpellCheck;

    @FXML
    private TextArea txtRisultato;

    @FXML
    private Label txtParoleSbagliate;

    @FXML
    private Button btnClear;

    @FXML
    private Label txtTempo;

    private Dictionary model;

    private ObservableList<String> scelte = FXCollections.observableArrayList();

    @FXML
    void doClearText(ActionEvent event) {

        this.txtDaControllare.clear();
        this.txtRisultato.clear();
        this.txtParoleSbagliate.setText("");
        this.txtTempo.setText("");

    }

    @FXML
    void doSpellCheck(ActionEvent event) {
        long tempo = System.currentTimeMillis();
        model.loadDictionary(this.choiceLingua.getValue());
        List<String> testo = new ArrayList<>();
        String stringa = new String(this.txtDaControllare.getText().toLowerCase());
        stringa = stringa.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]]", "");
        StringTokenizer tk = new StringTokenizer(stringa);
        while (tk.hasMoreTokens()) {
            testo.add(tk.nextToken());
        }
        int sbagliate = 0;
        for (RichWord r : model.splellCheckText(testo, true)) {
            if (!r.isCorretta()) {
                this.txtRisultato.appendText(r.getParola() + "\n");
                sbagliate++;
            }
        }
        this.txtParoleSbagliate.setText("The text contains " + sbagliate + " errors");
        this.txtTempo.setText("Spell check completed in "
                + (System.currentTimeMillis() - tempo) / 1000.0 + " seconds");

    }

    @FXML
    void initialize() {
        assert choiceLingua != null : "fx:id=\"choiceLingua\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtDaControllare != null : "fx:id=\"txtDaControllare\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtParoleSbagliate != null : "fx:id=\"txtParoleSbagliate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTempo != null : "fx:id=\"txtTempo\" was not injected: check your FXML file 'Scene.fxml'.";
        scelte.addAll("English", "Italian");
        this.choiceLingua.setItems(scelte);
        this.choiceLingua.setValue("Italian");
    }

    public void setModel(Dictionary model) {
        this.model = model;
    }
}