package it.polito.tdp.porto.model;

import org.jgrapht.graph.DefaultEdge;

public class DefaultPaper extends DefaultEdge {

	Paper paper;	
	/*public DefaultPaper(Paper paper) {
		super();
		this.paper = paper;
	}*/

		

		public Paper getPaper() {
			return paper;
		}

		public void setPaper(Paper paper) {
			this.paper = paper;
		}
		
		
	
}
