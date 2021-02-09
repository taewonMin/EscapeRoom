package member.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.handler.CommandHandler;
import member.service.IMemberService;
import member.service.MemberServiceImpl;
import vo.MemberVO;

public class LoginChkHandler implements CommandHandler{
	
	private static final String VIEW_PAGE = "/views/member/login.jsp";
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equals("GET")) {
			return VIEW_PAGE; 
		} else if(req.getMethod().equals("POST")) {
			
			String memId = req.getParameter("memId");
			String memPass = req.getParameter("memPass");
			
			IMemberService memberService = MemberServiceImpl.getInstance();
			
			if(memberService.newIdChk(memId) == 0) {
				res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			}else if(memberService.loginPassChk(memPass) == 0) {
				res.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
			}
			MemberVO memNo = memberService.getMemNo(memId);
			
			HttpSession session = req.getSession();
			
				if(memNo != null && memId.equals(memNo.getMemId())) {
					if(	memNo != null && memPass.equals(memNo.getMemPass())){
						session.setAttribute("memId", memId);
						session.setAttribute("memPass", memPass);
						session.setAttribute("memNo", memNo.getMemNo());
					}
				}
		}
		return null;
	}

}
