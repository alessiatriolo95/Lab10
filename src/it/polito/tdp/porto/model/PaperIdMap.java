package it.polito.tdp.porto.model;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class PaperIdMap {
	private Map<Integer,Paper> map;
	public PaperIdMap(){
		map= new HashMap<>();
	}
	public Paper getPaper(int code){
		return map.get(code);
	}
	
	public Paper put(Paper p){
		Paper old= map.get(p.getEprintid());
		if(old==null){
			map.put(p.getEprintid(),p);
			return p;
		}else{
			return old;
		}
	}
	public Collection<Paper> getMap() {
		return map.values();
	}
	
}
