package atweb.nhom6.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import atweb.nhom6.model.Lop;

public class LopDao {
	
	public List<Lop> getAllLop() {
		List<Lop> lops = new ArrayList<>();
		Connection conn = ConnectionToDB.getConnect();
		String sql = "select * from lop";
		Statement statement;
		try {
			statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				Lop l = new Lop();
				l.setMaLop(rs.getString(1));
				l.setTenLop(rs.getString(2));
				l.setMaNV(rs.getString(3));
				lops.add(l);
			}
			return lops;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
