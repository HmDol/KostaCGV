package KostaCGV;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conn.DBConnect;

public class MoviePurchaseDao {
	private DBConnect dbconn;

	public MoviePurchaseDao() {
		dbconn = DBConnect.getInstance();
	}

	public MoviePurchaseVo selectByPayCode(int paycode) {
		Connection conn = dbconn.conn();
		MoviePurchaseVo p = null;
		String sql = "select * from pay where payCode = ?";

		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, paycode);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				p = new MoviePurchaseVo(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getString(5),
						rs.getInt(6));
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
		return p;
	}

	// 예매목록보기
	public ArrayList<MoviePurchaseVo> selectAll() {

		ArrayList<MoviePurchaseVo> list = new ArrayList<MoviePurchaseVo>();
		Connection conn = dbconn.conn();
		String sql = "select * from pay where memid = ? ";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, MemberService.loginId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MoviePurchaseVo(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4),
						rs.getString(5), rs.getInt(6)));
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

	// 예매하기(영화구매내역 테이블에 담김)
	// 영화표 결제
	public void insert(MoviePurchaseVo p) {// insert는 파람으로 vo 한개 받아옴

		Connection conn = dbconn.conn();
		String sql = "insert into Pay values(seq_pay.nextval,?,sysdate,?,?,?)";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, p.getPayMethod());
			pstmt.setString(2, p.getMemId());
			pstmt.setString(3, p.getSeatCode());
			pstmt.setInt(4, p.getMovieCode());
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

	// payCode로 결제 취소
	public void delete(int payCode) {
		Connection conn = dbconn.conn();
		String sql = "delete pay where payCode=?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, payCode);
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