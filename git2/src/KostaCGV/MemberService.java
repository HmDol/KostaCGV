package KostaCGV;

import java.util.Scanner;

public class MemberService {
	private MemberDao dao;
	public static String loginId;// 로그인 아이디
	public static String loginName;// 로그인 닉네임

	public MemberService() {
		dao = new MemberDao();
	}

	// 회원가입
	public void join(Scanner sc) {

		System.out.println("=================================================");
		System.out.println("                   KOSTA CGV 회원가입              ");
		System.out.println("=================================================");
		String id;
		String name;
		MemberVo m = null;
		do {
			if (m != null) {
				System.out.println();
				System.out.println("<알림> 중복된 아이디가 있습니다.");
				System.out.println();
			}
			System.out.print("아이디를 입력해주세요 : ");
			id = sc.next();
			m = dao.select(id);
		} while (m != null); // id 중복되면 루프돔
		System.out.println();

		System.out.print("비밀번호를 입력해주세요 : ");
		String pwd = sc.next();
		System.out.println();

		MemberVo m1 = null;
		do {
			if (m != null) {
				System.out.println("<알림> 중복된 닉네임이 있습니다.");
				System.out.println();
			}
			System.out.print("닉네임을 입력해주세요 : ");
			sc.nextLine();
			name = sc.nextLine();
			m = dao.select(name);
		} while (m1 != null); // id 중복되면 루프돔
		System.out.println();

		System.out.print("핸드폰번호를 입력해주세요: ");
		String phone = sc.nextLine();
		System.out.println();

		System.out.print("이메일을 입력해주세요: ");
		String email = sc.nextLine();
		System.out.println();

		dao.insert(new MemberVo(id, pwd, name, phone, email));
	}

	// 로그인
	public void login(Scanner sc) {
		System.out.println("=================================================");
		System.out.println("                   KOSTA CGV 로그인                ");
		System.out.println("=================================================");
		System.out.print("아이디를 입력해주세요 : ");
		String id = sc.next();
		System.out.println();
		System.out.print("비밀번호를 입력해주세요 : ");
		String pwd = sc.next();
		System.out.println();
		MemberVo m = dao.select(id);
		if (m == null) {
			System.out.println("아이디가 존재하지 않습니다.");
			System.out.println();
		} else {
			if (pwd.equals(m.getPwd())) {
				System.out.println("로그인에 성공하셨습니다.");
				System.out.println();
				loginId = id;
				loginName = m.getName();
			} else {
				System.out.println("패스워드가 일치하지 않습니다.");
				System.out.println();
			}
		}
	}

	// 로그아웃
	public void logout() {
		System.out.println("로그아웃 되셨습니다.");
		System.out.println();
		if (loginId == null) {
			System.out.println("로그인을 해주세요.");
			System.out.println();
			return;
		}
		loginId = null;
	}

	// 내 정보 확인
	public void printMyInfo() {

		System.out.println("=======================================");
		System.out.println("                내 정보 보기              ");
		System.out.println("=======================================");
		if (loginId == null) {
			System.out.println("로그인을 해주세요.");
			return;
		}
		MemberVo m = dao.select(loginId);
		System.out.println("===============================");
		System.out.println("아이디 : " + m.getId());
		System.out.println();
		System.out.println("닉네임 : " + m.getName());
		System.out.println();
		System.out.println("휴대폰번호 : " + m.getPhone());
		System.out.println();
		System.out.println("이메일 : " + m.getEmail());
		System.out.println("===============================");
	}

	// 내 정보 수정
	public void editMyInfo(Scanner sc) {
		System.out.println("=======================================");
		System.out.println("                내 정보 수정              ");
		System.out.println("=======================================");
		if (loginId == null) {
			System.out.println("로그인을 해주세요.");
			return;
		}
		System.out.print("새로운 비밀번호를 입력해주세요 : ");
		String pwd = sc.next();
		System.out.println();
		System.out.print("새로운 닉네임을 입력해주세요 : ");
		String name = sc.next();
		System.out.println();
		dao.update(new MemberVo(loginId, pwd, name, "", ""));
	}

	// 회원 탈퇴
	public void delMember() {
		System.out.println("회원탈퇴가 완료되었습니다.");
		System.out.println("그 동안 KOSTA CGV를 이용해주셔서 감사합니다.");
		System.out.println();
		if (loginId == null) {
			System.out.println("로그인을 해주세요.");
			System.out.println();
			return;
		}
		dao.delete(loginId);
		loginId = null;
	}
}