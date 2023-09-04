package KostaCGV;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import conn.DBConnect;

public class PurchaseDao {
	private DBConnect dbconn;

	public PurchaseDao() {
		dbconn = DBConnect.getInstance();
	}

	// 구매목록보기
	public ArrayList<PurchaseVo> selectAll() {

		ArrayList<PurchaseVo> list = new ArrayList<PurchaseVo>();
		Connection conn = dbconn.conn();
		String sql = "select id, snack, sum(amount) as amount from purchase where id = ? group by id, snack";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, MemberService.loginId);
			ResultSet rs = pstmt.executeQuery();	
			while (rs.next()) {
				list.add(new PurchaseVo(rs.getString(1), rs.getString(2), rs.getInt(3)));
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
	public PurchaseVo select(String name) {

		Connection conn = dbconn.conn();
		String sql = "select * from Purchase where snack = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return new PurchaseVo(rs.getString(1), rs.getString(2), rs.getInt(3));
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

	// 주문하기(구매내역 테이블에 담김)
	public void insert(String id, String name, int amount) {

		Connection conn = dbconn.conn();
		String sql = "insert into purchase values (?, ?, ?)";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setInt(3, amount);
			int cnt = pstmt.executeUpdate();
			System.out.println("구매가 완료되었습니다.");
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

	// 구매취소하기
	public void delete_purchase(String name) {

		Connection conn = dbconn.conn();
		String sql = "delete from purchase where id = ? and snack = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, MemberService.loginId);
			pstmt.setString(2, name);
			int cnt = pstmt.executeUpdate();
			System.out.println("구매취소가 완료되었습니다.");
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
