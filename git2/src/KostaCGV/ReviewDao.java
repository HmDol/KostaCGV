package KostaCGV;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import conn.DBConnect;

public class ReviewDao {
	private DBConnect dbconn;

	public ReviewDao() {
		dbconn = DBConnect.getInstance();
	}

	// 리뷰 작성
	public void insert(ReviewVo r) {

		Connection conn = dbconn.conn();
		String sql = "insert into Review values(seq_review.nextval,?,?,?,sysdate,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, r.getReviewTitle());
			pstmt.setString(2, r.getContent());
			pstmt.setString(3, r.getName());
			pstmt.setInt(4, r.getRating());
			pstmt.setString(5, r.getId());

			int cnt = pstmt.executeUpdate();
			System.out.println("리뷰가 작성되었습니다.");

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

	// 글 번호로 검색
	public ReviewVo select(int num) {

		Connection conn = dbconn.conn();
		String sql = "select * from Review where reviewnum=?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return new ReviewVo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),
						rs.getInt(6), rs.getString(7));
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

	// 전체검색
	public ArrayList<ReviewVo> selectAll() {

		ArrayList<ReviewVo> list = new ArrayList<ReviewVo>();
		Connection conn = dbconn.conn();
		String sql = "select * from Review";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(new ReviewVo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),
						rs.getInt(6), rs.getString(7)));
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

	// 영화이름으로 검색해서 리뷰보기
	public ArrayList<ReviewVo> selectByTitle(String ReviewTitle) {

		ArrayList<ReviewVo> list = new ArrayList<ReviewVo>();
		Connection conn = dbconn.conn();
		String sql = "select * from Review where ReviewTitle like ?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + ReviewTitle + "%");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new ReviewVo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),
						rs.getInt(6), rs.getString(7)));
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

	// 리뷰내용수정
	public void update(ReviewVo r) {

		Connection conn = dbconn.conn();
		String sql = "update Review set content=? where reviewnum=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, r.getContent());
			pstmt.setInt(2, r.getReviewnum());
			int cnt = pstmt.executeUpdate();
			System.out.println("리뷰 수정이 완료되었습니다.");

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

	// 리뷰삭제
	public void delete(int reviewnum) {

		Connection conn = dbconn.conn();
		String sql = "delete Review where reviewnum=?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reviewnum);
			int cnt = pstmt.executeUpdate();
			System.out.println("리뷰 삭제가 완료되었습니다.");

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
