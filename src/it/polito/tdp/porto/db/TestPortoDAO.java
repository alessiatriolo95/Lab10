package it.polito.tdp.porto.db;

import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.AuthorIdMap;
import it.polito.tdp.porto.model.PaperIdMap;

public class TestPortoDAO {
	
	public static void main(String args[]) {
		PortoDAO pd = new PortoDAO();
		AuthorIdMap a= new AuthorIdMap();
		
		PaperIdMap p= new PaperIdMap();
		pd.getTuttigliAutori(a);
		pd.getTuttiPaper(p);
		System.out.println(pd.getTuttiPaperTranne(p, 1877,132));
		//System.out.println(pd.getArticolo(2293546));
		//System.out.println(pd.getArticolo(1941144));

	}

}
