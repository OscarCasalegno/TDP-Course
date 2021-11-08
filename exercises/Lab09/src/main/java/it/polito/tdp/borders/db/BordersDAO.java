package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {

	public void loadAllCountries(Map<String, Country> countryIdMap) {

		String sql = "SELECT ccode, StateAbb, StateNme FROM country ORDER BY StateAbb";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Country c = new Country(rs.getString("StateAbb"), rs.getInt("ccode"), rs.getString("StateNme"));
				if (!countryIdMap.containsKey(c.getAbbreviazione())) {
					countryIdMap.put(c.getAbbreviazione(), c);
				}
			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}

	public List<Border> getCountryPairs(int anno, Map<String, Country> countryIdMap) {

		String sql = "SELECT state1ab, state2ab " + "FROM contiguity "
				+ "WHERE conttype=1 AND state1no<state2no AND YEAR<?";

		List<Border> result = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, anno);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Country c1 = countryIdMap.get(rs.getString("state1ab"));
				Country c2 = countryIdMap.get(rs.getString("state2ab"));

				if (c1 == null || c2 == null) {
					System.err.println(
							"ERRORE COUNTRY PAIRS " + rs.getString("state1ab") + " " + rs.getString("state2ab"));
				} else {
					result.add(new Border(c1, c2));
				}

			}

			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}

	}
}
