package board.item.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.item.service.ItemService;
import board.item.service.ItemServiceImpl;
import common.handler.CommandHandler;
import vo.ItemVO;

public class SelectItemHandler implements CommandHandler{
	private static final String VIEW_PAGE = "/views/board/item/selectItemNew.jsp";
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		int itemNo = Integer.parseInt(req.getParameter("itemNo"));
		
		//1-1. 아이템정보 조회
		ItemService itemService = ItemServiceImpl.getInstance();
		
		ItemVO item = new ItemVO();
		item.setItemNo(itemNo);
		
		item = itemService.getItem(itemNo);
		req.setAttribute("item", item);
		
		return VIEW_PAGE;
	}

}
