package it.polito.tdp.porto.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Creator {
	private Paper paper;
	private List<Author> autori= new ArrayList<>();
	
	public Creator(Paper p,Author a) {
		// TODO Auto-generated constructor stub
		this.paper=p;
		autori.add(a);
	}
	
	public List<Author> getAutori(){
		return autori;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((paper == null) ? 0 : paper.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Creator other = (Creator) obj;
		if (paper == null) {
			if (other.paper != null)
				return false;
		} else if (!paper.equals(other.paper))
			return false;
		return true;
	}

	public Paper getPaper() {
		return paper;
	}

	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	@Override
	public String toString() {
		return "Creator [paper=" + paper + "]";
	}

	public void add(Author autore) {
		this.autori.add(autore);
		
	}

	

}
