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
import vo.ItemVO;
import vo.MemberVO;

public class MyPageBuyItemListHandler implements CommandHandler{
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		HttpSession session = req.getSession(true);
		
		IMemberService memberService = MemberServiceImpl.getInstance();
		
		try {
			
			String memNo = (String) session.getAttribute("memNo");
			List<ItemVO> itemList = memberService.getMemHasItemList(memNo); //멤버가 가진 아이템 리스트
			
			JsonResolver.view(res, itemList);
			
		}catch (SQLException e) {
			res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
			throw e;
		}
		
		return null;
	}

}
