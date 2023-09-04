package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// 싱글톤
// DB연결해서 사용할 connection 객체를 반환
public class DBConnect {

	private static DBConnect dbconn = new DBConnect();
	private String url = "jdbc:oracle:thin:@localhost:1521/xe";

	private DBConnect() {
	}

	public static DBConnect getInstance() {

		return dbconn;
	}

	public Connection conn() {
		// 드라이버 로드. 클래스 이름으로 찾아서 메모리에 얹음
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			// DB로그인. 세션수립하고 생성된 connection 객체 반환
			return DriverManager.getConnection(url, "hr", "hr");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
}