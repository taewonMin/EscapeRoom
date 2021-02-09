package game_mng.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.handler.CommandHandler;
import game_mng.controller.GameController;

public class GameStatusHandler implements CommandHandler {

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String memId = (String) req.getSession().getAttribute("memId");
		String key = req.getParameter("key");
		String value = req.getParameter("value");
		
		if(key.contains("메모")) {
			GameController.sendNotice("(#GameStatus Message#)" + key+"="+value);
		}else {
			GameController.sendNotice(memId + "님이 ["+ key +"] 의 문을 열었습니다. (#GameStatus Message#)" + key+"="+value);
		}
		
		return null;
	}

}
