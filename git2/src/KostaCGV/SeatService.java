package KostaCGV;

//service: 사용자에 제공할 기능 구현
import java.util.*;

public class SeatService {

	private SeatDao seatdao;

	public SeatService() {
		seatdao = new SeatDao();
	}

	// 좌석현황
	public void printALLseat(int mCode, String title, Date date, String time) {

		ArrayList<SeatVo> list = seatdao.seat_state(mCode);
		
		System.out.println("===========================================================================");
		System.out.println("                            " + title +" 좌석현황                              ");
		System.out.println("                          " + date + "  " + time + "                          ");
		System.out.println("===========================================================================");
		
		System.out.print("                       	   "); System.out.println("┌─────────────────┐");
		System.out.print("                       	   "); System.out.println("│       스크린	     │" );
		System.out.print("                       	   ");   System.out.print("└─────────────────┘");
		
		for (int i = 0; i < list.size(); i++) {

			if (i % 6 == 0) {
				System.out.println();
			}
			
			if (list.get(i).getIsres() == 1) {

				System.out.print("[" + list.get(i).getSeatcode() + "]");
				System.out.print("[예약] ");
			} else {
				System.out.print("[" + list.get(i).getSeatcode() + "]");
				System.out.print("[공석] ");
			}
		}
		System.out.println();
		System.out.println();
		System.out.println();
	}

	// 좌석취소(번호로)
	public void edit_seat(Scanner sc,String date, String time) {

		System.out.println("===============================좌석취소=============================");
		System.out.print("취소할 좌석코드 : ");
		String seatcode = sc.next();
		if(seatdao.seat_pre(seatcode)==1) seatdao.cancel_seat(seatcode);
		else System.out.println("이미 취소된 좌석입니다.");
	}
	
	// 좌석선택(번호로)
	public String choice_seat(Scanner sc, int moviecode) {

		System.out.println("===============================좌석선택=============================");
		System.out.print("선택할 좌석코드 : ");
		String seatcode = sc.next();
		if(seatdao.seat_pre(seatcode)==0) {seatdao.choice_seat(seatcode); return seatcode;}
		else System.out.println("이미 예약된 좌석입니다.");
		return null;
	}
}