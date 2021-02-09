package member.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.handler.CommandHandler;
import member.service.IMemberService;
import member.service.MemberServiceImpl;
import vo.MemberVO;

public class UpdateManagmentHandler implements CommandHandler {
	private IMemberService memberService = MemberServiceImpl.getInstance();

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String memBlack = req.getParameter("memBlack");
		String memNo = req.getParameter("memNo");
		System.out.println("핸들러블랙 : "+memBlack);
		System.out.println("핸들러넘버 : "+memNo);
		MemberVO mv = new MemberVO();
		mv.setMemBlack(memBlack);
		mv.setMemNo(memNo);
		
		memberService.updateManagment(mv);
		
		return null;
	}

}
