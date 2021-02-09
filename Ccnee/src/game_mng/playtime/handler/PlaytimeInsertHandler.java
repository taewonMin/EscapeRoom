package game_mng.playtime.handler;

import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.handler.CommandHandler;
import game_mng.controller.GameController;
import game_mng.playtime.service.PlaytimeService;
import game_mng.playtime.service.PlaytimeServiceImpl;
import vo.PlaytimeVO;

public class PlaytimeInsertHandler implements CommandHandler {

	private PlaytimeService ips = PlaytimeServiceImpl.getInstance();
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		// 게임 경과 시간
		long endTime = System.currentTimeMillis();
		long startTime = GameController.getStartTime();
		
		int gameTime = (int) ((endTime - startTime) / 1000);
		
		int hour = gameTime/3600;
		int minute = gameTime%3600/60;
		int second = gameTime%3600%60;
		
		DecimalFormat df = new DecimalFormat("00");
		String playRecord = df.format(hour) + ":" + df.format(minute) + ":" + df.format(second);
		
		
		// playtime 테이블에 기록
		PlaytimeVO pv = new PlaytimeVO();
		pv.setTeamCode(GameController.getTeamCode());
		pv.setPlayRecord(playRecord);
		pv.setPlayStatus("Y");
		
		try {
			ips.insertPlaytime(pv);
			
			GameController.sendNotice("게임이 종료되었습니다.(#GameStatus Message#)"+playRecord);
			
		}catch(SQLException e) {
			res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return null;
	}

}
