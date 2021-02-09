package game_mng.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.handler.CommandHandler;
import game_mng.controller.GameController;

public class GameEnterHandler implements CommandHandler{

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		if(GameController.getGameStatus()) {	// 게임이 진행중이면
			res.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE);
		}else if(GameController.getUserSize() == 4) {	// 게임 인원이 가득차면
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
		}
		
		return null;
	}

}
