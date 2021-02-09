package member.handler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.handler.CommandHandler;
import common.util.JsonResolver;
import member.service.IMemberService;
import member.service.MemberServiceImpl;
import vo.MemberVO;
import vo.PlaytimeVO;

public class MyPageMemberRankingHandler implements CommandHandler {

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//세션
		HttpSession session = req.getSession(true);
		
		IMemberService memberService = MemberServiceImpl.getInstance();
		
		List<PlaytimeVO> playList = new ArrayList<PlaytimeVO>();
		String memNo = (String) session.getAttribute("memNo");
		try {
			playList = memberService.memberRanking(memNo);
			
			JsonResolver.view(res, playList);
			
		}catch(SQLException e) {
			res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
			throw e;
		}
		
		return null;
	}

}
