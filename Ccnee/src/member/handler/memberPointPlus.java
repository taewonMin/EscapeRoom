package member.handler;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.handler.CommandHandler;
import member.service.IMemberService;
import member.service.MemberServiceImpl;
import vo.MemberVO;

public class memberPointPlus implements CommandHandler{

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		IMemberService memberService = MemberServiceImpl.getInstance();
		HttpSession session = req.getSession(true);
		
		String memId = (String) session.getAttribute("memId");
		int plusPoint = Integer.parseInt(req.getParameter("pt"));
		
		MemberVO member = new MemberVO();
		member.setMemId(memId);
		member.setMemPoint(plusPoint);
		
		memberService.plusPoint(member);
		
		return null;
	}
}
