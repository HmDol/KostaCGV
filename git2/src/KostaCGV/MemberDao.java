package KostaCGV;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conn.DBConnect;

public class MemberDao {

	private DBConnect dbconn;

	public MemberDao() {
		dbconn = DBConnect.getInstance();
	}

	public void insert(MemberVo m) {
		Connection conn = dbconn.conn();
		String sql = "insert into member values(?,?,?,?,?)";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getId());
			pstmt.setString(2, m.getPwd());
			pstmt.setString(3, m.getName());
			pstmt.setString(4, m.getPhone());
			pstmt.setString(5, m.getEmail());
			int cnt = pstmt.executeUpdate();

			System.out.println("회원가입이 완료되었습니다.");
			System.out.println("KOSTA CGV에 가입해주셔서 감사합니다.");
			System.out.println();

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

	public MemberVo select(String id) {

		Connection conn = dbconn.conn();
		String sql = "select * from member where id=?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return new MemberVo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5));
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

	public MemberVo select_name(String name) {

		Connection conn = dbconn.conn();
		String sql = "select * from member where name=?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return new MemberVo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5));
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

	public void update(MemberVo m) {

		Connection conn = dbconn.conn();
		String sql = "update member set pwd=?, name=? where id=?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getPwd());
			pstmt.setString(2, m.getName());
			pstmt.setString(3, m.getId());
			int cnt = pstmt.executeUpdate();
			System.out.println("내 정보 수정이 완료되었습니다.");

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

	public void delete(String id) {

		Connection conn = dbconn.conn();
		String sql = "delete member where id=?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			int cnt = pstmt.executeUpdate();
			System.out.println(cnt + "줄이 삭제됨");

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