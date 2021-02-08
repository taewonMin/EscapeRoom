package board.item.handler;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.item.service.BuyItemService;
import board.item.service.BuyItemServiceImpl;
import common.handler.CommandHandler;
import member.service.IMemberService;
import member.service.MemberServiceImpl;
import vo.BuyItemVO;
import vo.BuyVO;
import vo.MemberVO;

public class BuyItemHandler implements CommandHandler{
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return true;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//세션
		HttpSession session = req.getSession(true);
				
		BuyItemService buyItemService = BuyItemServiceImpl.getInstance();
		IMemberService memberService = MemberServiceImpl.getInstance();
		
		MemberVO member = new MemberVO();
		BuyVO buy = new BuyVO();
		BuyItemVO buyItem = new BuyItemVO();
		
		int itemNo = Integer.parseInt(req.getParameter("itemNo"));
		int itemPrice = Integer.parseInt(req.getParameter("itemPrice"));
		
		String memId = (String) session.getAttribute("memId");
		member.setMemId(memId);
		int memPoint = buyItemService.checkMemberPoint(member.getMemId()); //멤버의 현재 포인트
			
		int buyCnt = 0;
		int buyItemCnt = 0;
		String msg="";
	
		if(memPoint >= itemPrice) { //멤버 포인트가 아이템 가격보다 크거나 같으면
			buy.setItemNo(itemNo); //BuyVO에 먼저 세팅
			member = memberService.getMember(memId); //memId로 memNo 뽑아오기
			buy.setMemNo(member.getMemNo());
			buyCnt = buyItemService.insertBuy(buy);
			
//			buyItem.setItemNo(itemNo); //BuyitemVO에 세팅
//			buyItemCnt = buyItemService.insertBuyItem(buyItem);
			
			if(buyCnt > 0) { //만약 둘 다 삽입됐으면
				member.setMemPoint(memPoint - itemPrice); //memberVO에 세팅
				buyItemService.updateBuy(member);
				msg="success";
			}
			
		} else { //멤버 포인트가 더 작으면 list화면으로
			msg = "fail";
		}
			
		String redirectUrl = req.getContextPath()+"/views/board/item/listItem.do?msg="+URLEncoder.encode(msg,"UTF-8");
		return redirectUrl;
	}
}
