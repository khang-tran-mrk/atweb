package atweb.nhom6.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionToDB {
	public ConnectionToDB() {

	}

	public static Connection getConnect() {
		Connection connection = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String URL = "jdbc:sqlserver://localhost:1433; Database=FINALLTM; user=sa; password=123456";
			Connection con = DriverManager.getConnection(URL);
			return con;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return connection;

	}

	public void excuteSql(String sql) throws Exception { //
		Connection connect = getConnect();
		Statement stmt = connect.createStatement();

		stmt.executeUpdate(sql);
	}

	public ResultSet selectData(String sql) throws Exception { //
		Connection connect = getConnect();
		Statement stmt = connect.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		return rs;

	}

	public static void main(String[] args) throws Exception {
		ConnectionToDB connect = new ConnectionToDB();
		ResultSet rs = connect.selectData("select * from account");
		while(rs.next()) {
			System.out.println(rs.getString(3));
		}
	}
}
