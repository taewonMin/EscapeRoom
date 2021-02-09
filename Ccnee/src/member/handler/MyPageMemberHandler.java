package member.handler;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.handler.CommandHandler;
import common.util.JsonResolver;
import member.service.IMemberService;
import member.service.MemberServiceImpl;
import vo.ItemVO;
import vo.MemberVO;

public class MyPageMemberHandler implements CommandHandler{
	private static final String VIEW_PAGE = "/views/member/myPage.jsp";
			
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equals("GET")) {
			return VIEW_PAGE;
		}
		//세션
		HttpSession session = req.getSession(true);
		
		IMemberService memberService = MemberServiceImpl.getInstance();
		
		MemberVO member = new MemberVO();
		
		String memId = (String) session.getAttribute("memId");
		try {
			member = memberService.getMember(memId); //로그인 된 memId에 맞는 회원정보
			
			JsonResolver.view(res, member);
			
		}catch(SQLException e) {
			res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
			throw e;
		}

		return null;
	}
	
}
