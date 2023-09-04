package KostaCGV;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

import KostaCGV.MoviePurchaseVo;
import KostaCGV.MovieVo;
import KostaCGV.SeatService;

public class MoviePurchaseService {

	private MoviePurchaseDao moviepurchasedao;
	private MovieDao moviedao;
	private SeatService seatservice;
	private SeatDao seatdao;

	public MoviePurchaseService() {
		moviepurchasedao = new MoviePurchaseDao();
		moviedao = new MovieDao();
		seatservice = new SeatService();
		seatdao = new SeatDao();
	}

	// 예매하기
	public void bookMovie(Scanner sc) {
		System.out.println("=====================================");
		System.out.println("              예 매  하 기              ");
		System.out.println("=====================================");
		int moviecode = 0;
		printAllMovie(moviedao.printMoiveInfo()); // 영화 내역 보여지구
		System.out.print("예약할 영화 제목 : ");
		String title = sc.next();
		ArrayList<MovieVo> mlist = moviedao.selectMovieListByTitle(title);
		if (mlist.isEmpty()) {
			System.out.println("제목을 다시 확인해 주세요!");
			return;
		}
		// 날짜를 선택해 주세요
		System.out.println();
		System.out.println("------------예약 가능 날짜-----------");
		System.out.println("     " + mlist.get(0).getMovieDate() + "    " + mlist.get(6).getMovieDate());
		System.out.println("----------------------------------");
		System.out.print("날짜를 선택해주세요 : ");
		String mdate = sc.next();
		Date d = Date.valueOf(mdate);
		System.out.println();
		System.out.println("--------------------예약 가능 시간-------------------");
		System.out.print("   ");
		for (MovieVo m : mlist) {
			if (m.getMovieDate().equals(d)) {
				System.out.print(m.getStartTime() + "   ");
			}
		}
		System.out.println();
		System.out.println("--------------------------------------------------");	
		System.out.print("시간을 선택해주세요 : ");
		String mtime = sc.next();
		for (MovieVo m : mlist) {
			if (m.getMovieDate().equals(d) && mtime.equals(m.getStartTime())) {
				moviecode = m.getmovicode();
			}
		}

		seatservice.printALLseat(moviecode, title, d, mtime); // 좌석을 보여주고
		String seat = seatservice.choice_seat(sc, moviecode);
		moviepurchasedao.insert(new MoviePurchaseVo(0, "결제", new Date(0, 0, 0), KostaCGV.MemberService.loginId, seat, moviecode));

	}

	// 영화 목록 보기
	public void printAllMovie(ArrayList<MovieVo> list) {
		for (MovieVo m : list) {
			System.out.println("=====================================");
			System.out.println("영화제목 : " + m.getMovietitle());
			System.out.println();
			System.out.println("스토리 : " + m.getStory());
			System.out.println();
			System.out.println("장르 : " + m.getGenre());
			System.out.println("=====================================");
		}
	}

	// 예매목록보기
	public void printAll(ArrayList<MoviePurchaseVo> list) {

		System.out.println("=====================================");
		System.out.println("     " + MemberService.loginId + "님의 예매목록  ");
		System.out.println("=====================================");
		for (MoviePurchaseVo r : list) {
			
			System.out.println("===============================");
			System.out.println("결제코드 : " + r.getPayCode());
			System.out.println();
			MovieVo mv = moviedao.selectByCode(r.getMovieCode());
			System.out.println("영화제목 : " + mv.getMovietitle());
			System.out.println();
			System.out.println("상영날짜 : " + mv.getMovieDate());
			System.out.println();
			System.out.println("상영시간 : " + mv.getStartTime());
			System.out.println();
			System.out.println("좌석코드 : " + r.getSeatCode());
			System.out.println("===============================");
		}
	}

	// 리뷰전체 검색
	public void getAll() {

		ArrayList<MoviePurchaseVo> list = moviepurchasedao.selectAll();
		if (list.size() == 0) {
		}
		else
			printAll(list);
	}

	// 취소하기
	public void delete_purchase(Scanner sc) {
		System.out.println("=====================================");
		System.out.println("              예매 취소하기              ");
		System.out.println("=====================================");
		getAll();
		System.out.print("취소할 결제코드 : ");
		int paycode = sc.nextInt();
		MoviePurchaseVo r = moviepurchasedao.selectByPayCode(paycode);
		if (r == null) {
			System.out.println();
			System.out.println("예매목록에 없는 상품입니다.");
			System.out.println();
		} else {
			moviepurchasedao.delete(paycode);
			seatdao.cancel_seat(r.getSeatCode());
		}
	}
}