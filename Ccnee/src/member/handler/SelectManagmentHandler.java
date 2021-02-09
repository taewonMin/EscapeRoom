package member.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.handler.CommandHandler;
import common.util.JsonResolver;
import member.service.IMemberService;
import member.service.MemberServiceImpl;
import vo.BoardVO;
import vo.MemberVO;

public class SelectManagmentHandler implements CommandHandler {
	private IMemberService memberService = MemberServiceImpl.getInstance();

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String memNo = req.getParameter("memNo");
		MemberVO memberVO = memberService.selectMember(memNo);
		
		JsonResolver.view(res, memberVO);
		
		return null;
	}

}
