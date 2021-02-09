package member.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.handler.CommandHandler;
import member.service.IMemberService;
import member.service.MemberServiceImpl;

public class IdChkHandler implements CommandHandler{
	
	private static final String VIEW_PAGE = "views/member/newMember.jsp";
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equals("GET")) {
			return VIEW_PAGE;
		} else if(req.getMethod().equals("POST")) {
			
			String memId = req.getParameter("userId");
			
			String memEmail = req.getParameter("memEmail");
			String memTel = req.getParameter("memTel");
			
			IMemberService memberService = MemberServiceImpl.getInstance();
			
			int idChk = memberService.newIdChk(memId);
			int emailChk = memberService.newEmailChk(memEmail);
			int hpChk = memberService.newHpChk(memTel);
			
			if(idChk != 0) {
				res.getWriter().print(idChk);
			}
			else if(emailChk != 0) {
				res.getWriter().print(emailChk);
			}
			else if(hpChk != 0) {
				res.getWriter().print(hpChk);
			}
		}
		
		return null;
	}

}
