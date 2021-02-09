package game_mng.inven_item.handler;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.handler.CommandHandler;
import game_mng.inven_item.service.IInvenItemService;
import game_mng.inven_item.service.InvenItemServiceImpl;

public class DeleteItemHandler implements CommandHandler {

	private static IInvenItemService iis = InvenItemServiceImpl.getInstance();
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String itemNo = req.getParameter("itemNo");
		
		try {
			iis.deleteInvenItem(itemNo);
		}catch(SQLException e) {
			res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return null;
	}

}
