package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.User;

public class UserDAO {
	//ユーザーDTOクラス
	private User user = null;

	private static final String SQL_SELECT_USER_BY_NAME = "select * from user where name = ? and pass = ?";

	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection conn = null;
		//ドライバ認識 環境によっては不要
		Class.forName("com.mysql.cj.jdbc.Driver");
		// DBへの問い合わせ準備
		conn = DriverManager.getConnection("jdbc:mysql://localhost/dokotsubu", "root", "develop00");

		return conn;
	}

	public User selectByName(String name, String pass) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = this.getConnection();
			// DBへの問い合わせ準備
			stmt = conn.prepareStatement("select * from user where name = ? and pass = ?");
			stmt.setString(1, name);
			stmt.setString(2, pass);
			// 解説 : SELECT文の結果セット（テーブル形式）が、ResultSetオブジェクトに格納される
			rs = stmt.executeQuery();

			if (rs.next()) {
				// 解説 : ResultSet オブジェクトの現在行にあるデータを
				//        取得するために、 getXXX(YYY) メソッドを実行する。
				System.out.println("id" + "\t" + "name");
				System.out.println(rs.getInt("id")
						+ "\t" + rs.getString("name"));
				user = new User(rs.getInt("id"), rs.getString("name"), null);
				return user;
			}

			return user;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
			}
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
			}
		}

	}
}
