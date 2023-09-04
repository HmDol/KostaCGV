package KostaCGV;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conn.DBConnect;
import java.sql.Date;

public class MovieDao {

	private DBConnect dbconn;

	public MovieDao() {
		dbconn = DBConnect.getInstance();
	}

	// 영화 전체 리스트 검색
	public ArrayList<MovieVo> selectAll() {

		ArrayList<MovieVo> list = new ArrayList<MovieVo>();
		Connection conn = dbconn.conn();
		String sql = "select * from movie";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new MovieVo(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getString(5),
						rs.getString(6)));
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

	// 영화의 타이틀, 스토리, 장르 표시
	public ArrayList<MovieVo> printMoiveInfo() {

		ArrayList<MovieVo> list = new ArrayList<MovieVo>();
		Connection conn = dbconn.conn();
		String sql = "select  MIN(Moviecode)as id ,MovieTitle,Story,genre from Movie group by MovieTitle,Story,genre";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new MovieVo(1, rs.getString(2), new Date(0, 0, 0), "1", rs.getString(3), rs.getString(4)));
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

	// 영화 제목으로 리스트 반환
	public ArrayList<MovieVo> selectMovieListByTitle(String title) {
		ArrayList<MovieVo> list = new ArrayList<MovieVo>();

		Connection conn = dbconn.conn();

		String sql = "select * from Movie where MovieTitle = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setNString(1, title);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new MovieVo(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getString(5),
						rs.getString(6)));
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

	// 영화코드로 영화 검색.
	public MovieVo selectByCode(int moviecode) {
		Connection conn = dbconn.conn();
		String sql = "select * from Movie where MovieCode=?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, moviecode);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return new MovieVo(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getString(5),
						rs.getString(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close resources
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	// 영화 이름으로 영화 정보 검색.
	/*
	 * public MovieVo select(String movietitle) { Connection conn = dbconn.conn();
	 * String sql = "select * from Movie where MovieTitle=?";
	 * 
	 * try { PreparedStatement pstmt = conn.prepareStatement(sql);
	 * pstmt.setString(1, movietitle); ResultSet rs = pstmt.executeQuery();
	 * 
	 * if (rs.next()) { return new MovieVo(rs.getInt(1), rs.getString(2),
	 * rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)); } }
	 * catch (Exception e) { e.printStackTrace(); } finally { // Close resources try
	 * { conn.close(); } catch (Exception e) { e.printStackTrace(); } } return null;
	 * }
	 * 
	 * // 영화 장르로 영화 정보 검색 public ArrayList<MovieVo> selectBygenre(String genre) {
	 * ArrayList<MovieVo> list = new ArrayList<MovieVo>();
	 * 
	 * Connection conn = dbconn.conn();
	 * 
	 * String sql = "select * from movie where genre=?";
	 * 
	 * try { PreparedStatement pstmt = conn.prepareStatement(sql);
	 * 
	 * pstmt.setString(1, genre); ResultSet rs = pstmt.executeQuery();
	 * 
	 * while (rs.next()) { list.add(new MovieVo(rs.getInt(1), rs.getString(2),
	 * rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6))); } }
	 * catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace();
	 * 
	 * } finally { try { conn.close(); } catch (SQLException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } } return list; }
	 */
}