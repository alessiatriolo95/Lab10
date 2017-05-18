package it.polito.tdp.porto.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AuthorIdMap {
	private Map<Integer,Author> map;
	
	public AuthorIdMap(){
		map= new HashMap<>();
	}
	public Author getAutore(int code){
		return map.get(code);
	}
	public Author put(Author a){
		Author old= map.get(a.getId());
		if(old==null){
			map.put(a.getId(),a);
			return a;
		}else{
			return old;
		}
	}

	public Collection<Author> getMap() {
		return map.values();
	}
	
}
