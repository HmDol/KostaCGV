package KostaCGV;

import java.util.Scanner;

public class Controller {

	private MemberService memberservice;
	private ReviewService reviewservice;
	private MarketService marketservice;
	private PurchaseService purchaseservice;
	private MovieService movieservice;
	private SeatService seatservice;
	private MoviePurchaseService moviepurchaseservice;
	
	public Controller() {
		memberservice = new MemberService();
		reviewservice = new ReviewService();
		marketservice = new MarketService();
		purchaseservice = new PurchaseService();
		moviepurchaseservice = new MoviePurchaseService();
		movieservice = new MovieService();
		seatservice = new SeatService();
	}

	// 메인메뉴
	public void run1(Scanner sc) {
		boolean flag = true;
		int m = 0;
		while (flag) {

			System.out.println("=================================================");
			System.out.println("          KOSTA CGV에 오신것을 환영합니다!             ");
			System.out.println("=================================================");
			System.out.println("┌───────────────────────────────────────────────┐");
			System.out.println("│    1.회원가입   ||  2. 로그인   ||   3. 시스템종료 	│");
			System.out.println("└───────────────────────────────────────────────┘");
			System.out.print("번호 입력 : ");
			m = sc.nextInt();
			System.out.println();
			switch (m) {
			case 1:
				memberservice.join(sc);
				break;
			case 2:
				memberservice.login(sc);
				if (MemberService.loginId == null) {
					System.out.println("로그인 실패");
				} else {
					run2(sc);
				}
				break;
			case 3:
				System.out.println("KOSTA CGV를 종료합니다.");
				return;
			}
		}
	}

	// 로그인 후 메뉴
	public void run2(Scanner sc) {
		boolean flag = true;
		int m = 0;
		while (flag) {

			System.out.println("========================================================================");
			System.out.println("                               KOSTA CGV 메뉴                            ");
			System.out.println("========================================================================");
			System.out.println("┌───────────────────────────────────────────────────────────────────────┐");
			System.out.println("│   1. 내 정보   ||   2. 영화   ||   3. 리뷰   ||   4. 상점  ||  5. 로그아웃	│");
			System.out.println("└───────────────────────────────────────────────────────────────────────┘");
			System.out.print("번호 입력 : ");
			m = sc.nextInt();
			System.out.println();
			switch (m) {
			case 1:
				myinfo_run(sc); // 내정보로 넘어감
				flag = false;
				break;
			case 2:
				movie_run(sc);
				flag = false; // 영화예매로 넘어감
				break;
			case 3:
				reviewrun(sc); // 리뷰로 넘어감
				flag = false;
				break;
			case 4:
				marketrun(sc); // 상점으로 넘어감
				flag = false;
				break;
			case 5:
				memberservice.logout(); // 로그아웃하고 메인메뉴로 넘어감
				return;
			}
		}
	}

	// 내 정보메뉴
	public void myinfo_run(Scanner sc) {
		boolean flag = true;
		int m = 0;
		while (flag) {
			System.out.println("==================================================================================");
			System.out.println("                                     내 정보 메뉴                                    ");
			System.out.println("==================================================================================");
			System.out.println("┌────────────────────────────────────────────────────────────────────────────────┐");
			System.out.println("│  1. 내 정보보기  ||  2. 내 정보 수정  ||  3. 내 주문  ||  4. 이전화면  ||   5. 회원탈퇴  	 │");
			System.out.println("└────────────────────────────────────────────────────────────────────────────────┘");
			System.out.print("번호 입력 : ");
			m = sc.nextInt();
			System.out.println();
			switch (m) {
			case 1:
				memberservice.printMyInfo();
				break;
			case 2:
				memberservice.editMyInfo(sc);
				break;
			case 3:
				my_order(sc);
				flag = false;
				break;
			case 4:
				run2(sc);
				return;
			case 5:
				memberservice.delMember();
				run1(sc);
				flag = false;
				break;
			}
		}
	}

	// 영화메뉴
	public void movie_run(Scanner sc) {
		boolean flag = true;
		int m = 0;
		while (flag) {
			System.out.println("===================================");
			System.out.println("               영화 메뉴             ");
			System.out.println("===================================");
			System.out.println("┌─────────────────────────────────┐");
			System.out.println("│   1. 예약하기   ||   2. 이전메뉴 	  │");
			System.out.println("└─────────────────────────────────┘");
			System.out.print("번호 입력 : ");
			m = sc.nextInt();
			System.out.println();
			switch (m) {
			case 1:
				moviepurchaseservice.bookMovie(sc);
				break;
			case 2:
				run2(sc);
				return;
			}
		}
	}

	// 리뷰메뉴
	public void reviewrun(Scanner sc) {
		boolean flag = true;
		int m = 0;
		while (flag) {
			System.out.println("================================================================================");
			System.out.println("                                      리뷰 메뉴                                   ");
			System.out.println("================================================================================");
			System.out.println("┌───────────────────────────────────────────────────────────────────────────────┐");
			System.out.println("│   1. 리뷰보기   ||   2. 리뷰작성   ||   3. 리뷰삭제   ||   4. 리뷰수정   ||  5. 이전메뉴	│");
			System.out.println("└───────────────────────────────────────────────────────────────────────────────┘");
			System.out.print("번호 입력 : ");
			m = sc.nextInt();
			System.out.println();
			switch (m) {
			case 1:
				reviewservice.getAll();
				break;
			case 2:
				reviewservice.addReview(sc);
				break;
			case 3:
				reviewservice.delReview(sc);
				break;
			case 4:
				reviewservice.editReview(sc);
				break;
			case 5:
				run2(sc);
				return;
			}
		}
	}

	// 내주문메뉴
	public void my_order(Scanner sc) {
		boolean flag = true;
		int m = 0;
		while (flag) {
			System.out.println("===================================================================================");
			System.out.println("                                      내 주문 메뉴                                    ");
			System.out.println("===================================================================================");
			System.out.println("┌─────────────────────────────────────────────────────────────────────────────────┐");
			System.out.println("│   1. 구매내역   ||   2. 예매내역   ||   3. 구매취소   ||   4. 예매취소   ||   5. 이전메뉴	  │");
			System.out.println("└─────────────────────────────────────────────────────────────────────────────────┘");
			System.out.print("번호 입력 : ");
			m = sc.nextInt();
			System.out.println();
			switch (m) {
			case 1:
				purchaseservice.getAll();
				break;
			case 2:
				moviepurchaseservice.getAll();
				break;
			case 3:
				purchaseservice.delete_purchase(sc);
				break;
			case 4:
				moviepurchaseservice.delete_purchase(sc);
				break;
			case 5:
				myinfo_run(sc);
				return;
			}
		}
	}

	// 상점메뉴
	public void marketrun(Scanner sc) {
		boolean flag = true;
		int m = 0;
		while (flag) {
			System.out.println("====================================================");
			System.out.println("                         상점 메뉴                    ");
			System.out.println("====================================================");
			System.out.println("┌───────────────────────────────────────────────────┐");
			System.out.println("│   1. 판매목록보기   ||   2. 구매하기   ||   3. 이전메뉴   │");
			System.out.println("└───────────────────────────────────────────────────┘");
			System.out.print("번호 입력 : ");
			m = sc.nextInt();
			System.out.println();
			switch (m) {
			case 1:
				marketservice.getAll();
				break;
			case 2:
				marketservice.purchase(sc);
				break;
			case 3:
				run2(sc);
				return;
			}
		}
	}

	public void run(Scanner sc) {
		if (MemberService.loginId == null) { // 로그인 안된 상태
			run1(sc);
		} else { // 로그인 상태
			run2(sc);
		}
	}
}
