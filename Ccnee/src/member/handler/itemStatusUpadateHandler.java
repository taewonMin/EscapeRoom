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

public class itemStatusUpadateHandler implements CommandHandler {

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		IMemberService memberService = MemberServiceImpl.getInstance();
		String buyNo = req.getParameter("itemNo");
		
		memberService.updateItemStauts(buyNo);
		
		return null;
	}

}
