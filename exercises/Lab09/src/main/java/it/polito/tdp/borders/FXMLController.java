/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.borders;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;

	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;

	@FXML // fx:id="txtAnno"
	private TextField txtAnno; // Value injected by FXMLLoader

	@FXML // fx:id="cmbPaesi"
	private ComboBox<Country> cmbPaesi; // Value injected by FXMLLoader

	@FXML // fx:id="txtResult"
	private TextArea txtResult; // Value injected by FXMLLoader

	private Model model;

	@FXML
	void doCalcolaConfini(ActionEvent event) {

		int anno;
		try {
			anno = Integer.parseInt(this.txtAnno.getText());
		} catch (NumberFormatException e) {
			this.txtResult.setText("Inserire solo numeri nel campo Anno");
			return;
		}

		if (anno < 1816 || anno > 2016) {
			this.txtResult.setText("Inserire un anno compreso tra 1816 e 2016");
			return;
		}

		this.model.creaGrafo(anno);

		this.txtResult.setText("Nel grafo sono presenti " + this.model.compConn() + " componenti connesse\n");

		Map<Country, Integer> paesi = this.model.paesi();

		for (Country c : paesi.keySet()) {
			this.txtResult.appendText(c.toString() + " " + paesi.get(c) + "\n");
		}

	}

	@FXML
	void doCalcolaVicini(ActionEvent event) {

		Country co = this.cmbPaesi.getValue();

		if (co == null) {
			this.txtResult.setText("Scegliere un paese");
			return;
		}

		this.txtResult.setText("Paesi raggiungibili: \n");

		Set<Country> vicini = this.model.getVicini(co);

		if (vicini == null) {
			this.txtResult.appendText("Nessun paese raggiungibile via terra");
		} else {
			for (Country c : vicini) {
				this.txtResult.appendText(c.toString() + "\n");
			}
		}

	}

	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
		assert cmbPaesi != null : "fx:id=\"cmbPaesi\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

	}

	public void setModel(Model model) {
		this.model = model;

		this.cmbPaesi.setItems(FXCollections.observableArrayList(model.getAllCountries()));
	}
}
