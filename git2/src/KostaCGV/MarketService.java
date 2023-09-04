package KostaCGV;

import java.util.ArrayList;
import java.util.Scanner;

public class MarketService {
	private MarketDao dao;
	private PurchaseDao purchasedao;

	public MarketService() {
		dao = new MarketDao();
		purchasedao = new PurchaseDao();
	}

	// 리뷰보기
	public void printAll(ArrayList<MarketVo> list) {

		System.out.println("=====================================");
		System.out.println("                판매목록               ");
		System.out.println("=====================================");
		for (MarketVo r : list) {

			System.out.println("===============================");
			System.out.println("물품명 : " + r.getSnack());
			System.out.println("가격 : " + r.getPrice());
			System.out.println("수량 : " + r.getAmount());
			System.out.println("===============================");
		}
	}

	// 리뷰전체 검색
	public void getAll() {

		ArrayList<MarketVo> list = dao.selectAll();
		if (list.size() == 0)
			System.out.println("상품이 없습니다.");
		else
			printAll(list);
	}

	// 구매하기
	public void purchase(Scanner sc) {
		System.out.println("=====================================");
		System.out.println("                구매 하기              ");
		System.out.println("=====================================");
		System.out.print("구매할 상품명 : ");
		String name = sc.next();
		System.out.println();
		System.out.print("구매할 수량 : ");
		int amount = sc.nextInt();
		System.out.println();
		MarketVo r = dao.select(name);
		if (r == null) {
			System.out.println("없는 상품입니다.");
			System.out.println();
		} else {
			purchasedao.insert(MemberService.loginId, name, amount);
			dao.update(name, amount); // 마켓테이블 수량변경해야됨
		}
	}
}
