package member.handler;

import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.handler.CommandHandler;
import member.service.IMemberService;
import member.service.MemberServiceImpl;
import vo.MemberVO;

public class MyPageMemberOutHandler implements CommandHandler {
	private static final String VIEW_PAGE = "/views/member/outMember.jsp";
			
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
		IMemberService memberService = MemberServiceImpl.getInstance();
		HttpSession session = req.getSession(true); //세션
		String memId = (String) session.getAttribute("memId");
		
		if(req.getMethod().equals("GET")) {
			//1. 회원 정보 조회
			MemberVO member = new MemberVO();
			member = memberService.getMember(memId); //memId에 맞는 회원정보 담음
			
			req.setAttribute("memberVO", member);
			
			return VIEW_PAGE;
			
		} else if(req.getMethod().equals("POST")){
			
			MemberVO member = new MemberVO();
			member = memberService.getMember(memId);
			
			int cnt = 0;
			
			try {
				cnt = memberService.outMember(memId);
			} catch(Exception e) {
				e.printStackTrace();
				throw new ServletException(e);
			}
			
			String msg = "";
			
			if(cnt > 0) {
				msg = "수정-성공";
			} else {
				msg = "수정-실패";
			}
			
			session.invalidate();

			String redirectUrl = "/CCNEE/";
			
			return redirectUrl;
		}
		return null;
	}
}
