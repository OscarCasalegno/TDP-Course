/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.PremierLeague;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.PremierLeague.model.Adiacenza;
import it.polito.tdp.PremierLeague.model.Match;
import it.polito.tdp.PremierLeague.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	Model model;

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="btnCreaGrafo"
	private Button btnCreaGrafo; // Value injected by FXMLLoader

	@FXML // fx:id="btnConnessioneMassima"
	private Button btnConnessioneMassima; // Value injected by FXMLLoader

	@FXML // fx:id="btnCollegamento"
	private Button btnCollegamento; // Value injected by FXMLLoader

	@FXML // fx:id="txtMinuti"
	private TextField txtMinuti; // Value injected by FXMLLoader

	@FXML // fx:id="cmbMese"
	private ComboBox<String> cmbMese; // Value injected by FXMLLoader

	@FXML // fx:id="cmbM1"
	private ComboBox<Match> cmbM1; // Value injected by FXMLLoader

	@FXML // fx:id="cmbM2"
	private ComboBox<Match> cmbM2; // Value injected by FXMLLoader

	@FXML // fx:id="txtResult"
	private TextArea txtResult; // Value injected by FXMLLoader

	@FXML
	void doConnessioneMassima(ActionEvent event) {
		this.txtResult.clear();

		if (this.model.getConnessioneMassima() == null) {
			this.txtResult.appendText("Creare il grafo");
			return;
		}

		this.txtResult.appendText("Coppie con connnsessione massima:\n");

		for (Adiacenza a : this.model.getConnessioneMassima()) {
			this.txtResult.appendText(a.toString() + "\n");
		}

	}

	@FXML
	void doCreaGrafo(ActionEvent event) {
		this.txtResult.clear();

		Integer min;
		try {
			min = Integer.parseInt(this.txtMinuti.getText());
		} catch (NumberFormatException e) {
			this.txtResult.appendText("Inserire un numero valido di minuti");
			return;
		}

		if (min < 0) {
			this.txtResult.appendText("Inserire un numero valido di minuti");
			return;
		}

		String mese = this.cmbMese.getValue();
		if (mese == null) {
			this.txtResult.appendText("Scegliere un mese");
			return;
		}

		this.model.creaGrafo(mese, min);

		this.txtResult.appendText("Grafo creato\n");
		this.txtResult.appendText("#Vertici: " + this.model.getNumeroVertici() + "\n");
		this.txtResult.appendText("#Archi: " + this.model.getNumeroArchi() + "\n");

		this.cmbM1.getItems().clear();
		this.cmbM2.getItems().clear();

		this.cmbM1.getItems().addAll(this.model.getVertici());
		this.cmbM2.getItems().addAll(this.model.getVertici());

	}

	@FXML
	void doCollegamento(ActionEvent event) {
		this.txtResult.clear();
		Match m1 = this.cmbM1.getValue();
		Match m2 = this.cmbM2.getValue();

		if (m1 == null || m2 == null) {
			this.txtResult.appendText("Scegliere entrambi i match");
			return;
		}

		if (m1.equals(m2)) {
			this.txtResult.appendText("Scegliere due match diversi");
			return;
		}

		if (m1.getTeamAwayID().equals(m2.getTeamHomeID()) && m2.getTeamAwayID().equals(m1.getTeamHomeID())) {
			this.txtResult.appendText("Scegliere due match tra squadre diverse");
			return;
		}

		this.model.cercaCollegamento(m1, m2);

		if (this.model.getPercorso() == null) {
			this.txtResult.appendText("Nessun percorso trovato");
			return;
		}

		this.txtResult.appendText("Peso totale cammino: " + this.model.getPesoMax().intValue() + "\n");
		this.txtResult.appendText("Cammino: \n");

		for (Match m : this.model.getPercorso()) {
			this.txtResult.appendText(m.toString() + "\n");
		}

	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnConnessioneMassima != null : "fx:id=\"btnConnessioneMassima\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnCollegamento != null : "fx:id=\"btnCollegamento\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtMinuti != null : "fx:id=\"txtMinuti\" was not injected: check your FXML file 'Scene.fxml'.";
		assert cmbMese != null : "fx:id=\"cmbMese\" was not injected: check your FXML file 'Scene.fxml'.";
		assert cmbM1 != null : "fx:id=\"cmbM1\" was not injected: check your FXML file 'Scene.fxml'.";
		assert cmbM2 != null : "fx:id=\"cmbM2\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

	}

	public void setModel(Model model) {
		this.model = model;

		List<String> mesi = new ArrayList<>();

		mesi.add("Gennaio");
		mesi.add("Febbraio");
		mesi.add("Marzo");
		mesi.add("Aprile");
		mesi.add("Maggio");
		mesi.add("Giugno");
		mesi.add("Luglio");
		mesi.add("Agosto");
		mesi.add("Settembre");
		mesi.add("Ottobre");
		mesi.add("Novembre");
		mesi.add("Dicembre");

		this.cmbMese.getItems().addAll(mesi);
	}

}
