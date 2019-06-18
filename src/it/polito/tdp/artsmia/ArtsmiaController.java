/**
 * Sample Skeleton for 'Artsmia.fxml' Controller Class
 */

package it.polito.tdp.artsmia;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.artsmia.model.Model;
import it.polito.tdp.artsmia.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ArtsmiaController {
	
	Model model;
	Integer annoScelto = null;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxAnno"
    private ChoiceBox<Integer> boxAnno; // Value injected by FXMLLoader

    @FXML // fx:id="txtFieldStudenti"
    private TextField txtFieldStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void handleCreaGrafo(ActionEvent event) {
    	
    	annoScelto = boxAnno.getValue();
    	if(annoScelto == null) {
    		txtResult.setText("Selezionare un anno");
    		return;
    	}

    	model.creaGrafo(annoScelto);
    	txtResult.setText("Grafo fortemente connesso?" + model.isStronglyConnected());
    	txtResult.appendText("\nLa mostra con più opere dall'anno " + annoScelto+" e' "+model.getMostraMassima(annoScelto).toString());
    }

    @FXML
    void handleSimula(ActionEvent event) {
    	
    	Integer numeroStudenti = null;
    	try {
    		numeroStudenti = Integer.parseInt(txtFieldStudenti.getText());
    	}catch(NumberFormatException e ) {
    		txtResult.setText("Inserisci un numero intero");
    		return;
    	}
    	
    	model.simula(numeroStudenti);
    	txtResult.clear();
    	
    	for(Studente s : model.getListaStudenti()) {
    		txtResult.appendText(s.toString()+"\n");
    	}

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxAnno != null : "fx:id=\"boxAnno\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtFieldStudenti != null : "fx:id=\"txtFieldStudenti\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Artsmia.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		boxAnno.getItems().addAll(model.getAllYears());
	}
}
