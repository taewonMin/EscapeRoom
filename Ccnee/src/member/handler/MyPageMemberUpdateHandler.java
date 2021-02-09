package member.handler;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.handler.CommandHandler;
import member.service.IMemberService;
import member.service.MemberServiceImpl;
import vo.MemberVO;

public class MyPageMemberUpdateHandler implements CommandHandler{
	private static final String VIEW_PAGE = "/views/member/updateMember.jsp";
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		if(req.getMethod().equals("GET")) {
			return false;
		} else {
			return true; 
		}
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession(true); //세션
		IMemberService memberService = MemberServiceImpl.getInstance();
		String memId = (String) session.getAttribute("memId"); //로그인된 아이디
		
		if(req.getMethod().equals("GET")) {
			
			//1. 회원 정보 조회
			MemberVO member = new MemberVO();
			member = memberService.getMember(memId); //memId에 맞는 회원정보 담음
			
			req.setAttribute("memberVO", member);
			
			return VIEW_PAGE;
			
		} else if(req.getMethod().equals("POST")){
			
			//1. 요청 파라미터 정보 가져오기
			String memName = req.getParameter("memName");
			String memPass = req.getParameter("memPass");
			String memTel = req.getParameter("memTel");
			String memEmail = req.getParameter("memEmail");
			
			//2. 회원 정보 수정
			MemberVO member = new MemberVO();
			member = memberService.getMember(memId);
			
			member.setMemName(memName);
			member.setMemPass(memPass);
			member.setMemTel(memTel);
			member.setMemEmail(memEmail);
			
			int cnt = memberService.updateMember(member);
			
			String msg = "";
			
			if(cnt > 0) {
				msg = "수정-성공";
			} else {
				msg = "수정-실패";
			}
			
			//마이페이지 화면으로 리다이렉트
			String redirectUrl = req.getContextPath() + "/views/member/myPageMember.do?msg="+ URLEncoder.encode(msg,"UTF-8");
			
			return redirectUrl;
		}
		return null;
	}
}
