package atweb.nhom6.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import atweb.nhom6.model.ACCOUNT;

public class SignUpDAO {
	public int add(ACCOUNT account) {
		String INSERT_USER_SQL = "insert into ACCOUNT (username, password, email, sdt) values (?, ?, ?, ?)";
		int result = 0;
				
		//ket noi db
		Connection connection = new ConnectionToDB().getConnect();
		
		try {
			PreparedStatement preStmt = connection.prepareStatement(INSERT_USER_SQL);
			
			preStmt.setString(1, account.getUsername());
			preStmt.setString(2, account.getPassword());
			preStmt.setString(3, account.getEmail());
			preStmt.setString(4, account.getSdt());
			
			//thuc hien them account vao
			result = preStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return result;
	}
}
