package it.polito.tdp.porto.model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.Multigraph;

import it.polito.tdp.porto.db.PortoDAO;

public class Model {
	private UndirectedGraph<Author, DefaultPaper> graph;
	private List<Author> autori;
	private List<Paper> papers;
	
	public List<Author> getAutori(){
	PortoDAO p= new PortoDAO();
	AuthorIdMap a= new AuthorIdMap();
		if(autori== null){
			autori =p.getTuttigliAutori(a);
			
		}
			return autori;
		
	}
	public List<Paper> getPapers(){
		PortoDAO p= new PortoDAO();
		PaperIdMap pa= new PaperIdMap();
		if(this.papers==null){
			papers=p.getTuttiPaper(pa);
			
		}
		return papers;
	}
	
	public void creaGrafo(){
		PortoDAO p= new PortoDAO();
		AuthorIdMap a= new AuthorIdMap();
		List<Author> au=p.getTuttigliAutori(a);
		//System.out.println(au);
		PaperIdMap pa= new PaperIdMap();
		List<Paper> pap=p.getTuttiPaper(pa);
		
		 graph= new Multigraph <Author,DefaultPaper>(DefaultPaper.class);
		 Graphs.addAllVertices(graph, this.getAutori());
		 
		 for(Creator c: p.getTuttiAutoriDatoPaper(pa, a).values()){
			 for(Author a1: c.getAutori()){
				 for(Author a2: c.getAutori()){
					 if(!a1.equals(a2)){
					DefaultPaper d=graph.addEdge(a1, a2);
					 d.setPaper(c.getPaper());;
					 }
				 }
			 }
		 }
		// System.out.println(p.getTuttiAutoriDatoPaper(pa, a));
	
	}
	public Collection<Author> getVicini(Author au){
		Map<Integer,Author> ris= new HashMap<>();
		for(Author a :Graphs.neighborListOf(this.GetGrafo(), au) ){
			if( !ris.containsKey(a.getId()))
				ris.put(a.getId(), a);
			
		}
		return ris.values();
	}
	public Collection<Author> getNonVicini(Collection<Author> lista, Author au){
		List<Author> nuova= new ArrayList<Author>(this.getAutori());
		nuova.remove(au);
		for(Author a: this.getVicini(au)){
			nuova.remove(a);
			
		}
		return nuova;
		
	}
	List<Paper> lista= new LinkedList<Paper>(); 
	public List<Paper> getLista(Author a1, Author a2){
	/*	List<Paper> ris= new LinkedList<Paper>();
		//List<Paper> dopo= new LinkedList<Paper>();
		AuthorIdMap a= new AuthorIdMap();

		PaperIdMap pa= new PaperIdMap();
		PortoDAO p= new PortoDAO();
		p.getTuttigliAutori(a);
		p.getTuttiPaper(pa);
		
		
		for(Paper articolo: p.getTuttiPaperTranne(pa, a1.getId(), a2.getId())){
			ris.add(articolo);
		}
		for(Paper articolo1: p.getTuttiPaperInsieme(pa, a1.getId(), a2.getId())){
			ris.add(articolo1);
		}
		return ris;*/
		DijkstraShortestPath<Author,DefaultPaper> cammino= new DijkstraShortestPath<Author,DefaultPaper>(this.GetGrafo(), a1, a2);
		for(DefaultPaper p: cammino.getPathEdgeList()){
			lista.add(p.getPaper());
		}
		return lista;
	
	}
	
	
	public UndirectedGraph<Author, DefaultPaper> GetGrafo(){
		if(graph==null){
			this.creaGrafo();
			
		}
		return this.graph;
	}
	
}
