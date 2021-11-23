package atweb.nhom6.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import atweb.nhom6.model.ACCOUNT;

public class SignInDAO {

	public int check(ACCOUNT account) {
		String CHECK_USER_SQL = "select username from ACCOUNT where username = ? and password = ?";
		int result = 0;

		// ket noi db
		Connection connection = new ConnectionToDB().getConnect();

		try {
			PreparedStatement preStmt = connection.prepareStatement(CHECK_USER_SQL);

			preStmt.setString(1, account.getUsername());
			preStmt.setString(2, account.getPassword());

			// thuc hien them account vao
			ResultSet rs = preStmt.executeQuery();
			
			if (rs.next()) {
				result = 1;
				System.out.println("numrow = " + rs.getRow());
			}	
			else
				result = 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
