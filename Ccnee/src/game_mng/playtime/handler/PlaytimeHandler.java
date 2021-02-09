package game_mng.playtime.handler;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.handler.CommandHandler;
import game_mng.playtime.service.PlaytimeService;
import game_mng.playtime.service.PlaytimeServiceImpl;
import vo.PlaytimeVO;

public class PlaytimeHandler implements CommandHandler{
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
			return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equals("GET")) {
			PlaytimeService playtimeService = PlaytimeServiceImpl.getInstance();
			
			
			List<PlaytimeVO> playtimeList = playtimeService.displayAll();
			
			String teamCode = "";
			System.out.println(playtimeList.size());
			
			for(int i=0; i<playtimeList.size(); i++) {
				System.out.println(playtimeList.size());
				teamCode = playtimeList.get(i).getTeamCode();
				List<String> playMemIdList = playtimeService.getPlayMemId(teamCode);
				System.out.println("playMemIdList : " + playMemIdList.size());
				req.setAttribute("playMemIdList", playMemIdList);
			}
			
			
			
			req.setAttribute("playtimeList", playtimeList);
			
			
//			String redirectURL = req.getContextPath() + "/playtime/play.do";
//			
//			return redirectURL;
		} else if (req.getMethod().equals("POST")) {
			PlaytimeService playtimeService = PlaytimeServiceImpl.getInstance();
			
			
			List<PlaytimeVO> playtimeList = playtimeService.displayAll();
			
			String teamCode = "";
			
			for(int i=0; i<playtimeList.size(); i++) {
				
				teamCode = playtimeList.get(i).getTeamCode();
			}
			
			List<String> playMemIdList = playtimeService.getPlayMemId(teamCode);
			
			
			req.setAttribute("playtimeList", playtimeList);
			req.setAttribute("playMemIdList", playMemIdList);
			
			String redirectURL = req.getContextPath() + "/playtime/play.do";
			
			return redirectURL;
		}
		return null;
	}

}
