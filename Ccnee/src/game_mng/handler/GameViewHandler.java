package game_mng.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.handler.CommandHandler;

public class GameViewHandler implements CommandHandler {

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String viewNo = req.getParameter("viewNo");
		String back = req.getParameter("back");
		
		String resultUrl = "/views/game/view"+viewNo+".jsp";
		
		if(back != null) {	// 회의실에서 복도로 돌아간경우
			resultUrl += "?back="+back;
		}
		
		return resultUrl;
	}

}
