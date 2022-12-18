package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConnectionFactory {

	private static final String DRIVER = "org.postgresql.Driver";
	private static final String URL = "jdbc:postgresql://localhost:5432/puzzle";
	private static final String USER = "postgres";
	private static final String PASS = "root";

	public static Connection getConnection() {

		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL, USER, PASS);

		} catch (ClassNotFoundException | SQLException ex) {
			throw new RuntimeException("Erro na conexão!!!", ex);
		}
	}

	public static void closeConnection(Connection conn) {

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException ex) {
				System.err.println("Erro na conexão!!!" + ex);
			}
		}
	}

	public static void closeConnection(Connection conn, PreparedStatement stmt) {

		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException ex) {
				System.err.println("Erro na conexão!!!" + ex);
			}
		}
		closeConnection(conn);
	}

	public static void closeConnection(Connection conn, PreparedStatement stmt, ResultSet rs) {

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				System.err.println("Erro na conexão!!!" + ex);
			}
		}
		closeConnection(conn, stmt);
	}

}