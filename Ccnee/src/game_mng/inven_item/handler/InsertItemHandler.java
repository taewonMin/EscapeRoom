package game_mng.inven_item.handler;

import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import common.handler.CommandHandler;
import game_mng.controller.GameController;
import game_mng.inven_item.service.IInvenItemService;
import game_mng.inven_item.service.InvenItemServiceImpl;
import vo.Inven_ItemVO;

public class InsertItemHandler implements CommandHandler {

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
		
		ObjectMapper mapper = new ObjectMapper();
		Inven_ItemVO iiv = mapper.readValue(req.getReader(), Inven_ItemVO.class);
		
		try {
		iis.insertInvenItem(iiv.toInvenItemVO());
		
		// 메세지 보내기
		String memId = (String) req.getSession().getAttribute("memId");
		String itemName = iiv.getInvenItemInfo().split("\\$\\$")[0];
		
		GameController.sendNotice(memId + "님이 ["+ itemName +"] 아이템을 획득하셨습니다. (#GameStatus Message#)" + iiv.getSessionData());
		}catch(SQLException e) {
			res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return null;
	}

}
