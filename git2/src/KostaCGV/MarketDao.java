package KostaCGV;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import conn.DBConnect;

public class MarketDao {
	private DBConnect dbconn;

	public MarketDao() {
		dbconn = DBConnect.getInstance();
	}

	// 판매목록보기
	public ArrayList<MarketVo> selectAll() {

		ArrayList<MarketVo> list = new ArrayList<MarketVo>();
		Connection conn = dbconn.conn();
		String sql = "select * from market";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MarketVo(rs.getString(1), rs.getInt(2), rs.getInt(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return list;
	}

	// 상품명으로 검색
	public MarketVo select(String name) {

		Connection conn = dbconn.conn();
		String sql = "select * from Market where snack = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return new MarketVo(rs.getString(1), rs.getInt(2), rs.getInt(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	// 구매하기(마켓테이블 변경됨)
	public void update(String name, int amount) {

		Connection conn = dbconn.conn();
		String sql = "update market set amount = amount - ? where snack = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, amount);
			pstmt.setString(2, name);
			int cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 취소하면 마켓테이블 수량증가
	public void update_cancel(String name, int amount) {

		Connection conn = dbconn.conn();
		String sql = "update market set amount = amount + ? where snack = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, amount);
			pstmt.setString(2, name);
			int cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
