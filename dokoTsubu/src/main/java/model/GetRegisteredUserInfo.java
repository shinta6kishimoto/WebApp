package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GetRegisteredUserInfo {
	public GetRegisteredUserInfo() {

	}

	public boolean execute(String userName, String pass) {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			//ドライバ認識 環境によっては不要
			Class.forName("com.mysql.cj.jdbc.Driver");
			// DBへの接続
			conn = DriverManager.getConnection("jdbc:mysql://localhost/dokotsubu", "root", "develop00");
			// DBへの問い合わせ準備
			stmt = conn.prepareStatement("select * from user where name = ? and pass = ?");
			stmt.setString(1, userName);
			stmt.setString(2, pass);
			// 解説 : SELECT文の結果セット（テーブル形式）が、ResultSetオブジェクトに格納される
			rs = stmt.executeQuery();

			if (rs.next()) {
				// 解説 : ResultSet オブジェクトの現在行にあるデータを
				//        取得するために、 getXXX(YYY) メソッドを実行する。
				System.out.println("id" + "\t" + "name");
				System.out.println(rs.getInt("id")
						+ "\t" + rs.getString("name"));
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
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
