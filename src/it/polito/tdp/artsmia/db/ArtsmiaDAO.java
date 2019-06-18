package it.polito.tdp.artsmia.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.artsmia.model.ArtObject;
import it.polito.tdp.artsmia.model.Mostra;

public class ArtsmiaDAO {

	public List<ArtObject> listObject() {
		
		String sql = "SELECT * from objects";

		List<ArtObject> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				result.add(new ArtObject(res.getInt("object_id"), res.getString("classification"), res.getString("continent"), 
						res.getString("country"), res.getInt("curator_approved"), res.getString("dated"), res.getString("department"), 
						res.getString("medium"), res.getString("nationality"), res.getString("object_name"), res.getInt("restricted"), 
						res.getString("rights_type"), res.getString("role"), res.getString("room"), res.getString("style"), res.getString("title")));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<Integer> getAllYears() {

		String sql = "SELECT DISTINCT begin " + 
					"FROM exhibitions " + 
					"ORDER BY begin ASC ";

		List<Integer> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				result.add(res.getInt("begin"));
			}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<Mostra> getMostre(Integer annoScelto) {
		String sql = "SELECT * " + 
				"FROM exhibitions AS e " + 
				"WHERE e.`begin`>= ? " + 
				"ORDER BY begin ASC  ";

	List<Mostra> result = new ArrayList<>();

	Connection conn = DBConnect.getConnection();

	try {
		PreparedStatement st = conn.prepareStatement(sql);
		st.setInt(1, annoScelto);
		ResultSet res = st.executeQuery();

		while (res.next()) {
			
			result.add( new Mostra(res.getInt("exhibition_id"), 
					res.getString("exhibition_department"),
					res.getString("exhibition_title"),
					res.getInt("begin"), res.getInt("end")));
		}

		conn.close();
		return result;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	}

	public Map<Mostra, Integer> getMostraOpere(Integer annoScelto, Map<Integer, Mostra> idMapMostre) {
		String sql = "SELECT eo.exhibition_id,  COUNT(eo.object_id) AS cnt " + 
				"FROM exhibition_objects AS eo, exhibitions e " + 
				"WHERE e.exhibition_id = eo.exhibition_id " + 
				"AND e.`begin`>= ? " + 
				"GROUP BY eo.exhibition_id";

	Map<Mostra, Integer> result = new HashMap<>();

	Connection conn = DBConnect.getConnection();

	try {
		PreparedStatement st = conn.prepareStatement(sql);
		st.setInt(1, annoScelto);
		ResultSet res = st.executeQuery();

		while (res.next()) {
			Mostra m = idMapMostre.get(res.getInt("eo.exhibition_id"));
			result.put(m, res.getInt("cnt") );
		}

		conn.close();
		return result;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	}

	public List<Integer> getIdOpereMostra(Mostra mostra) {
		String sql = "SELECT object_id from exhibition_objects where exhibition_id = ?";

		List<Integer> result = new ArrayList<>();

		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, mostra.getId());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				
				result.add(res.getInt("object_id"));
				}

			conn.close();
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
}
