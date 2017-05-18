package it.polito.tdp.porto.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.porto.model.Author;
import it.polito.tdp.porto.model.AuthorIdMap;
import it.polito.tdp.porto.model.Creator;
import it.polito.tdp.porto.model.Paper;
import it.polito.tdp.porto.model.PaperIdMap;

public class PortoDAO {
	
	public List<Author> getTuttigliAutori(AuthorIdMap a){

		final String sql = "SELECT * FROM author";
		List<Author> autori= new ArrayList<Author>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Author autore = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"));
				autore= a.put(autore);
				autori.add(autore);
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}

		return autori;

	}
	
	public List<Paper> getTuttiPaper(PaperIdMap p){
		PaperIdMap classe= new PaperIdMap();
			final String sql = "SELECT * FROM paper";
			List<Paper> papers= new ArrayList<Paper>();
			try {
				Connection conn = DBConnect.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				

				ResultSet rs = st.executeQuery();

				while (rs.next()) {

					Paper paper = new Paper(rs.getInt("eprintid"), rs.getString("title"), rs.getString("issn"),
							rs.getString("publication"), rs.getString("type"), rs.getString("types"));
					paper= p.put(paper);
					papers.add(paper);
				}
			} catch (SQLException e) {
				// e.printStackTrace();
				throw new RuntimeException("Errore Db");
			}

			return papers;

		}
	/*
	 * Dato l'id ottengo l'autore.
	 */
	public Author getAutore(int id) {

		final String sql = "SELECT * FROM author where id=?";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {

				Author autore = new Author(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"));
				return autore;
			}

			return null;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	public Map<Paper,Creator> getTuttiAutoriDatoPaper(PaperIdMap p, AuthorIdMap a) {
		
		final String sql = "SELECT * FROM creator";
		
		Map<Paper,Creator> map= new HashMap<>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				Author autore = a.getAutore(rs.getInt("authorid"));
				Paper paper = p.getPaper((rs.getInt("eprintid")));
				if(!autore.getPaper().contains(paper)){
				autore.add(paper);
				}
				if(!map.containsKey(paper)){
				Creator collaborazione= new Creator(paper,autore);
				map.put(paper, collaborazione);
				}else{
					map.get(paper).add(autore);
				}
				
			}

			return map;
		}

		 catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}

	}
public List<Paper> getTuttiPaperTranne(PaperIdMap p,int a1,int a2) {
		
		final String sql = "SELECT * FROM creator WHERE creator.authorid=? AND creator.eprintid NOT IN( SELECT eprintid FROM creator WHERE creator.authorid=?)";
		List<Paper> papers= new ArrayList<Paper>();
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1,a1);
			st.setInt(2,a2);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Paper paper= p.getPaper(rs.getInt("eprintid"));
				papers.add(paper);
				
			}

			return papers;
		}

		 catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}

	}
public List<Paper> getTuttiPaperInsieme(PaperIdMap p,int a1,int a2) {
	
	final String sql = "SELECT * FROM creator WHERE creator.authorid=? AND creator.eprintid  IN( SELECT eprintid FROM creator WHERE creator.authorid=? )";
	List<Paper> papers= new ArrayList<Paper>();
	try {
		Connection conn = DBConnect.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		st.setInt(1,a1);
		st.setInt(2,a2);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			Paper paper= p.getPaper(rs.getInt("eprintid"));
			papers.add(paper);
			
		}

		return papers;
	}

	 catch (SQLException e) {
		// e.printStackTrace();
		throw new RuntimeException("Errore Db");
	}

}

	/*
	 * Dato l'id ottengo l'articolo.
	 */
	public Paper getArticolo(int eprintid) {

		final String sql = "SELECT * FROM paper where eprintid=?";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, eprintid);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				Paper paper = new Paper(rs.getInt("eprintid"), rs.getString("title"), rs.getString("issn"),
						rs.getString("publication"), rs.getString("type"), rs.getString("types"));
				return paper;
			}

			return null;

		} catch (SQLException e) {
			 e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
}