package KostaCGV;

import java.util.ArrayList;
import java.util.Scanner;

public class PurchaseService {
	private PurchaseDao dao;
	private MarketDao marketdao;

	public PurchaseService() {
		dao = new PurchaseDao();
		marketdao = new MarketDao();
	}

	// 구매목록보기
	public void printAll(ArrayList<PurchaseVo> list) {

		System.out.println("=====================================");
		System.out.println("  " + MemberService.loginId + "님의 구매목록  ");
		System.out.println("=====================================");
		for (PurchaseVo r : list) {
			
			System.out.println("===============================");
			System.out.println("상품명 : " + r.getSnack());
			System.out.println();
			System.out.println("구매수량 : " + r.getAmount());
			System.out.println();
			System.out.println("===============================");
		}
	}

	// 리뷰전체 검색
	public void getAll() {

		ArrayList<PurchaseVo> list = dao.selectAll();
		if (list.size() == 0)
			System.out.println("구매상품이 없습니다.");
		else
			printAll(list);
	}

	// 취소하기
	public void delete_purchase(Scanner sc) {
		System.out.println("=====================================");
		System.out.println("              구매 취소하기              ");
		System.out.println("=====================================");
		System.out.print("취소할 상품명 : ");
		String name = sc.next();
		System.out.println();
		PurchaseVo r = dao.select(name);
		if (r == null) {
			System.out.println("구매목록에 없는 상품입니다.");
			System.out.println();
		} else {
			dao.delete_purchase(name);
			marketdao.update_cancel(name, r.getAmount());
		}
	}
}
