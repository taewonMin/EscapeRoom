package member.handler;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.handler.CommandHandler;
import member.service.IMemberService;
import member.service.MemberServiceImpl;
import vo.MemberVO;

public class InsertMemberHandler implements CommandHandler{
	
	private static final String VIEW_PAGE = "/views/member/newMember.jsp";
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		if(req.getMethod().equals("GET")) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		if(req.getMethod().equals("GET")) {
			return VIEW_PAGE;
		}else if(req.getMethod().equals("POST")) {
			
			String memId = req.getParameter("memId");
			String memName = req.getParameter("memName");
			String memPass = req.getParameter("memPass");
			String memTel = req.getParameter("memTel");
			String memEmail = req.getParameter("memEmail");
			int memBir = Integer.parseInt(req.getParameter("memBir")+req.getParameter("memBir2")+req.getParameter("memBir3"));
			
			IMemberService memberService = MemberServiceImpl.getInstance();
			
			MemberVO mv = new MemberVO();
			mv.setMemId(memId);
			mv.setMemName(memName);
			mv.setMemPass(memPass);
			mv.setMemTel(memTel);
			mv.setMemEmail(memEmail);
			mv.setMemBir(memBir);
			
//			String memNo = "mem" + 00000;
			
			int cnt = memberService.newMember(mv);
			
			String msg = "";
			if(cnt > 0) {
				msg = "성공";
			}else {
				msg = "실패";
			}
			
			String redirectUrl = req.getContextPath()
					+ "?msg=" + URLEncoder.encode(msg, "utf-8");
			
			return "/CCNEE/views/member/loginMember.do";
			
		}
		
		return null;
	}

}
