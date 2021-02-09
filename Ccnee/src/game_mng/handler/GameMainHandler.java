package game_mng.handler;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.handler.CommandHandler;

public class GameMainHandler implements CommandHandler {

	private static String VIEW_PAGE = "/views/game/main.jsp";
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		return VIEW_PAGE + "?gameNo=" + req.getParameter("gameNo");
	}

}
