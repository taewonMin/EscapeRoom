package game_mng.inven_item.handler;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import common.handler.CommandHandler;
import common.util.JsonResolver;
import game_mng.inven_item.service.IInvenItemService;
import game_mng.inven_item.service.InvenItemServiceImpl;
import vo.Inven_ItemVO;

public class ReadItemHandler implements CommandHandler {

	private IInvenItemService iis = InvenItemServiceImpl.getInstance();
	public void setIis(IInvenItemService iis) {
		this.iis = iis;
	}
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String invenCode = req.getParameter("invenCode");
		
		try {
			List<Inven_ItemVO> invenItemList = new ArrayList<Inven_ItemVO>(); 
			invenItemList = iis.getInvenItem(invenCode);
			
			JsonResolver.view(res, invenItemList);
			
		}catch (SQLException e) {
			res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
			throw e;
		}
		
		return null;
	}

}
