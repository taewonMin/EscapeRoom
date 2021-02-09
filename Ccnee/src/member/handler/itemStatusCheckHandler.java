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
import vo.BuyVO;
import vo.ItemVO;

public class itemStatusCheckHandler implements CommandHandler {

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		IMemberService memberService = MemberServiceImpl.getInstance();
		HttpSession session = req.getSession(true);
		try {
			
			String memNo = (String) session.getAttribute("memNo");
			List<BuyVO> buyList = memberService.itemStatusCheck(memNo);
			
			JsonResolver.view(res, buyList);
			
		}catch (SQLException e) {
			res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
			throw e;
		}
		
		return null;
	}

}
