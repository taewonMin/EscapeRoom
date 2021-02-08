package board.item.handler;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.item.service.ItemService;
import board.item.service.ItemServiceImpl;
import common.handler.CommandHandler;

public class DeleteItemHandler implements CommandHandler{

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return true;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//1. 요청 파라미터 정보 가져오기
		int itemNo = Integer.parseInt(req.getParameter("itemNo"));
		
		//2. 서비스 객체 생성하기
		ItemService itemService = ItemServiceImpl.getInstance();
		
		//3. 아이템 정보 삭제
		int cnt = itemService.deleteItemBoard(itemNo);
		
		String msg = "";
		if(cnt > 0) {
			msg ="성공";
		}else {
			msg ="실패";
		}
		
		//4. 목록 조회화면으로 리다이렉트
		String redirectUrl = req.getContextPath() + "/views/board/item/listItem.do?msg=" + URLEncoder.encode(msg,"UTF-8");
		
		return redirectUrl;
	}

}
