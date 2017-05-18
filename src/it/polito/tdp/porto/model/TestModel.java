package it.polito.tdp.porto.model;

import java.awt.List;

import it.polito.tdp.porto.db.PortoDAO;

public class TestModel {

	public static void main(String[] args) {
		
		Model m= new Model();
		Author a1= new Author(132,"Bona","Basilio");
		Author a2= new Author(1877,"Tadei","Roberto");
		m.creaGrafo();
		System.out.println(m.getLista(a1, a2));
	}

}
