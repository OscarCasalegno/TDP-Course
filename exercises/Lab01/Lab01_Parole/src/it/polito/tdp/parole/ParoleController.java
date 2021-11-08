package it.polito.tdp.parole;

/**
 * Sample Skeleton for 'Parole.fxml' Controller Class
 */


import it.polito.tdp.parole.model.*;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ParoleController {
	
	Parole[] elenco;
	String selezionato;
	Long[] tempo;
	TextArea[] txtRisultati;
	TextArea[] txtTempi;

	 @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private TextField txtParola;

	    @FXML
	    private Button btnInserisci;

	    @FXML
	    private TextArea txtResultList;

	    @FXML
	    private TextArea txtTempiList;

	    @FXML
	    private TextArea txtResultArray;

	    @FXML
	    private TextArea txtTempiArray;

	    @FXML
	    private TextField txtConfronto;

	    @FXML
	    private Button btnCancella;

	    @FXML
	    private Button btnReset;


    @FXML
    void doInsert(ActionEvent event) {
    	if(txtParola.getText().isEmpty()) {
    		return;
    	}
    	
    	for(int i=0; i<elenco.length; i++) {
    		tempo[i]=System.nanoTime();
    		elenco[i].addParola(txtParola.getText());
    		String parole=new String();
    		for(String s: elenco[i].getElenco())
    			parole+=s+"\n";
    		txtRisultati[i].clear();
    		txtRisultati[i].appendText(parole);
    		tempo[i]=System.nanoTime()-tempo[i];
    		txtTempi[i].appendText("Inserimento: "+tempo[i]+"\n");
    	}
    	
    	this.confronto(tempo);
    	
    	txtParola.clear();
    }
    
    
    void confronto(Long[] tempo) {
    	txtConfronto.clear();
    	if(tempo[0]<tempo[1])
    		txtConfronto.appendText("List più veloce");
    	else if(tempo[1]<tempo[0])
    		txtConfronto.appendText("Array più veloce");
    	else
    		txtConfronto.appendText("Stessa velocità");
	}

	
    @FXML
    void doReset(ActionEvent event) {
		txtParola.clear();
		for(int i=0; i<elenco.length; i++) {
    		tempo[i]=System.nanoTime();
			elenco[i].reset();
    		txtRisultati[i].clear();
    		tempo[i]=System.nanoTime()-tempo[i];
    		txtTempi[i].appendText("Reset: "+tempo[i]+"\n");
    	}
    	this.confronto(tempo);
    }
    
    
    @FXML
    void doCancella(ActionEvent event) {
    	txtParola.clear();
    	if(selezionato.isEmpty()) {
    		return;
    	}
    	for(int i=0; i<elenco.length; i++) {
    		tempo[i]=System.nanoTime();
    		elenco[i].delParola(selezionato);
    		String parole=new String();
    		for(String s: elenco[i].getElenco())
    			parole+=s+"\n";
    		txtRisultati[i].clear();
    		txtRisultati[i].appendText(parole);
    		tempo[i]=System.nanoTime()-tempo[i];
    		txtTempi[i].appendText("Cancella: "+tempo[i]+"\n");
    	}
    	
    	this.confronto(tempo);
    	
    }
    
    
    @FXML
    void doSelezione(MouseEvent event) {
    	
    	if(txtResultList.isFocused())
    		selezionato=txtResultList.getSelectedText();
    	else
    		selezionato=txtResultArray.getSelectedText();
    }

    
    @FXML
    void initialize() {
    	assert txtParola != null : "fx:id=\"txtParola\" was not injected: check your FXML file 'Parole.fxml'.";
        assert btnInserisci != null : "fx:id=\"btnInserisci\" was not injected: check your FXML file 'Parole.fxml'.";
        assert txtResultList != null : "fx:id=\"txtResultList\" was not injected: check your FXML file 'Parole.fxml'.";
        assert txtTempiList != null : "fx:id=\"txtTempiList\" was not injected: check your FXML file 'Parole.fxml'.";
        assert txtResultArray != null : "fx:id=\"txtResultArray\" was not injected: check your FXML file 'Parole.fxml'.";
        assert txtTempiArray != null : "fx:id=\"txtTempiArray\" was not injected: check your FXML file 'Parole.fxml'.";
        assert txtConfronto != null : "fx:id=\"txtConfronto\" was not injected: check your FXML file 'Parole.fxml'.";
        assert btnCancella != null : "fx:id=\"btnCancella\" was not injected: check your FXML file 'Parole.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Parole.fxml'.";
 
        selezionato=new String();
       
        // [0] per List e [1] per array
        elenco=new Parole[2];
        elenco[0] = new ParoleList();
        elenco[1]=new ParoleArray();
        tempo=new Long[2];
        txtRisultati=new TextArea[2];
        txtRisultati[0]=txtResultList;
        txtRisultati[1]=txtResultArray;
        txtTempi=new TextArea[2];
        txtTempi[0]=txtTempiList;
        txtTempi[1]=txtTempiArray;
        
    }
}
