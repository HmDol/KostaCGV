package KostaCGV;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import conn.DBConnect;

public class SeatDao {

	private DBConnect dbconn;

	public SeatDao() {
		dbconn = DBConnect.getInstance();
	}

	// 좌석현황
	public ArrayList<SeatVo> seat_state(int mCode) {

		Connection conn = dbconn.conn();
		ArrayList<SeatVo> list = new ArrayList<SeatVo>();
		String sql = "select * from seat where moviecode = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mCode);
			ResultSet rs = pstmt.executeQuery();
			SeatVo vo = null;
			while (true) {
				if (rs.next()) {
					vo = new SeatVo(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4));
					list.add(vo);
				} else
					break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

	public void cancel_seat(String seatcode) { // 좌석취소

		Connection conn = dbconn.conn();

		String sql = "update seat set isres = 0 where seatcode = ?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,seatcode);
			int cnt = pstmt.executeUpdate();
			System.out.println();
			System.out.println("좌석번호 " + seatcode + "가 취소되었습니다.");
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

	// 좌석선택 (isres = 1로 변경)
	public void choice_seat(String seatcode) {

		Connection conn = dbconn.conn();

		String sql = "update seat set isres = 1 where seatcode = '" + seatcode + "'";

		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			int cnt = pstmt.executeUpdate();
			System.out.println();
			System.out.println("좌석번호" + seatcode + "가 예약되었습니다.");
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
//	public void choice_seat(String seatcode, int moviecode) {
//
//		Connection conn = dbconn.conn();
//
//		String sql = "update seat set isres = 1 where seatcode = '" + seatcode + "' and moviecode = ?";
//
//		try {
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, moviecode);
//			int cnt = pstmt.executeUpdate();
//			System.out.println("좌석번호" + seatcode + "가 선택(예약)되었습니다.");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}

	// seatcode로 좌석이 공석(0)인지 (1)예약인지 확인
	public int seat_pre(String seatcode) {

		Connection conn = dbconn.conn();
		String sql = "select * from seat where seatcode = '" + seatcode + "'";
		ArrayList<SeatVo> list = new ArrayList<SeatVo>();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			SeatVo vo = null;
			while (true) {
				if (rs.next()) {
					vo = new SeatVo(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4));
					return vo.getIsres();
				} else
					break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}



}