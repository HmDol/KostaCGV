package KostaCGV;

import java.util.ArrayList;
import java.util.Scanner;

public class MovieService {

	private MovieDao dao;

	public MovieService() {
		dao = new MovieDao();
	}

	// 영화목록보기
	public void printAll(ArrayList<MovieVo> list) {

		System.out.println("=====================================");
		System.out.println("                영화목록               ");
		System.out.println("=====================================");
		for (MovieVo r : list) {

			System.out.println("영화제목 : " + r.getMovietitle());
			System.out.println("상영날짜 : " + r.getMovieDate());
			System.out.println("상영시작시간 : " + r.getStartTime());
			System.out.println("스토리 : " + r.getStory());
			System.out.println("장르 : " + r.getGenre());
			System.out.println();
		}
	}

	// 리뷰전체 검색
	public void getAll() {

		ArrayList<MovieVo> list = dao.selectAll();
		if (list.size() == 0)
			System.out.println("영화목록이 없습니다.");
		else
			printAll(list);
	}

	// 영화 제목으로 영화 정보 조회하기
	/*public void searchByTitle(Scanner sc) {
		System.out.println(" 제목으로검색하기 ");
		System.out.print("MovieTitle : ");
		String title = sc.next();
		MovieVo M = dao.select(title);
		if (M == null) {
			System.out.println("정보가없습니다");
		} else {
			System.out.println(M);
		}
	}

	// 영화 장르로 검색하기
	public void getBygenre(Scanner sc) {
		System.out.println("장르로 검색하기");
		System.out.print("genre : ");
		String genre = sc.next();
		ArrayList<MovieVo> list = dao.selectBygenre(genre);
		print(list);
	}

	public void print(ArrayList<MovieVo> list) {
		for (MovieVo M : list) {
			System.out.println(M);
		}
	}*/

}