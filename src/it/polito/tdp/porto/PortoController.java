package it.polito.tdp.porto;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.Model;
import it.polito.tdp.porto.model.Paper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class PortoController {
	Model m;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Author> boxPrimo;

    @FXML
    private ComboBox<Author> boxSecondo;

    @FXML
    private Button btnCoautori;

    @FXML
    private Button btnSeqArticoli;

    @FXML
    private TextArea txtResult;

    @FXML
    void handleCoautori(ActionEvent event) {
    	
    	if(this.boxPrimo.getValue()!=null){
    		
    		for(Author a: m.getVicini(this.boxPrimo.getValue())){
    			txtResult.appendText( a+"\n");
    		}
    	this.boxSecondo.getItems().addAll(m.getNonVicini(m.getVicini(this.boxPrimo.getValue()), this.boxPrimo.getValue()));
    		
    	}
    }

    @FXML
    void handleSequenza(ActionEvent event) {
    	if(this.boxPrimo.getValue()!=null && this.boxSecondo.getValue()!=null){
    		if(m.getLista(this.boxPrimo.getValue(),this.boxSecondo.getValue())!=null){
    		for(Paper p: m.getLista(this.boxPrimo.getValue(),this.boxSecondo.getValue())){
    			txtResult.appendText(p+"\n");
    		}}else{
    			txtResult.appendText("nessun articolo trovato");
    		}
    	}
    }
    @FXML
    void initialize() {
        assert boxPrimo != null : "fx:id=\"boxPrimo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert boxSecondo != null : "fx:id=\"boxSecondo\" was not injected: check your FXML file 'Porto.fxml'.";
        assert btnCoautori != null : "fx:id=\"btnCoautori\" was not injected: check your FXML file 'Porto.fxml'.";
        assert btnSeqArticoli != null : "fx:id=\"btnSeqArticoli\" was not injected: check your FXML file 'Porto.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Porto.fxml'.";

    }

	public void setM(Model m) {
		this.m = m;
		this.boxPrimo.getItems().addAll(m.getAutori());
		
	}
}
