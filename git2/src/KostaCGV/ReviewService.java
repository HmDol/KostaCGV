package KostaCGV;

import java.util.ArrayList;
import java.util.Scanner;

public class ReviewService {
	private ReviewDao dao;

	public ReviewService() {
		dao = new ReviewDao();
	}

	// 리뷰보기
	public void printAll(ArrayList<ReviewVo> list) {
		
		System.out.println("=====================================");
		System.out.println("                리뷰 보기              ");
		System.out.println("=====================================");
		
		for (ReviewVo r : list) {

			System.out.println("===============================");
			System.out.println("글번호 : " + r.getReviewnum());
			System.out.println();
			System.out.println("영화제목 : " + r.getReviewTitle());
			System.out.println();
			System.out.println("글쓴이 : " + r.getName());
			System.out.println();
			System.out.println("날짜 : " + r.getWdate());
			System.out.println();
			System.out.println("내용 : " + r.getContent());
			System.out.println("===============================");

		}
	}

	// 리뷰전체 검색
	public void getAll() {

		ArrayList<ReviewVo> list = dao.selectAll();
		if (list.size() == 0)
			System.out.println("리뷰가 없습니다.");
		else
			printAll(list);
	}

	// 리뷰작성
	public void addReview(Scanner sc) {
		System.out.println("=====================================");
		System.out.println("                리뷰 작성              ");
		System.out.println("=====================================");
		System.out.print("영화제목 : ");
		sc.nextLine();
		String ReviewTitle = sc.nextLine();
		System.out.println();
		System.out.print("별점(1~5): ");
		int rating = sc.nextInt();
		System.out.println();
		System.out.print("내용 : ");
		sc.nextLine();
		String content = sc.nextLine();
		System.out.println();
		dao.insert(new ReviewVo(0, ReviewTitle, content, MemberService.loginName, null, rating, MemberService.loginId));
	}

	// 수정(자기글)
	public void editReview(Scanner sc) {
		System.out.println("=====================================");
		System.out.println("                리뷰 수정              ");
		System.out.println("=====================================");
		System.out.print("수정할 리뷰번호: ");
		int num = sc.nextInt();
		ReviewVo r = dao.select(num);
		if (r == null) {
			System.out.println("없는 글입니다.");
			System.out.println();
		} else {
			if (MemberService.loginId.equals(r.getId())) {

				System.out.println("============= 수정 전============");
				System.out.println("글번호 : " + r.getReviewnum());
				System.out.println();
				System.out.println("영화제목 : " + r.getReviewTitle());
				System.out.println();
				System.out.println("글쓴이 : " + r.getName());
				System.out.println();
				System.out.println("날짜 : " + r.getWdate());
				System.out.println();
				System.out.println("내용 : " + r.getContent());
				System.out.println("===============================");
				System.out.print("수정할 내용을 입력 : ");
				sc.nextLine();
				String content = sc.nextLine();
				r.setContent(content);
				System.out.println();
				dao.update(r);
			} else {
				System.out.println("본인글만 수정 가능합니다.");
				System.out.println();
			}
		}
	}

	// 삭제(자기글)
	public void delReview(Scanner sc) {
		System.out.println("=====================================");
		System.out.println("                리뷰 삭제              ");
		System.out.println("=====================================");
		System.out.print("삭제할 리뷰번호: ");
		int reviewnum = sc.nextInt();
		System.out.println();
		ReviewVo r = dao.select(reviewnum);
		if (r == null) {
			System.out.println("없는 글입니다.");
			System.out.println();
		} else {
			if (MemberService.loginId.equals(r.getId())) {
				dao.delete(reviewnum);
			} else {
				System.out.println("본인글만 삭제 가능합니다.");
				System.out.println();
			}
		}
	}
}
