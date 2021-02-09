package game_mng.handler;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.item.service.BuyItemService;
import board.item.service.BuyItemServiceImpl;
import common.handler.CommandHandler;
import common.util.JsonResolver;
import game_mng.controller.GameController;
import member.service.IMemberService;
import member.service.MemberServiceImpl;
import vo.MemberVO;

public class GameEndHandler implements CommandHandler {

	private static String VIEW_PAGE = "/views/game/gameEnd.jsp";
	
	private IMemberService ims = MemberServiceImpl.getInstance();
	private BuyItemService bis = BuyItemServiceImpl.getInstance();
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equals("GET")) {
			return VIEW_PAGE;
		}
		
		// 게임 경과 시간
		long endTime = System.currentTimeMillis();
		long startTime = GameController.getStartTime();
		
		int gameTime = (int) ((endTime - startTime) / 1000);
		
		int hour = gameTime/3600;
		int minute = gameTime%3600/60;
		int second = gameTime%3600%60;
		
		DecimalFormat df = new DecimalFormat("00");
		String record = df.format(hour) + ":" + df.format(minute) + ":" + df.format(second);
		
		// 팀원의 정보 List 생성
		List<MemberVO> memList = new ArrayList<MemberVO>();
		
		Set<String> memIdList = GameController.getUserIdList();
		for(String memId : memIdList) {
			MemberVO member = ims.getMember(memId);
			memList.add(member);
			
			// 포인트 증가
			member.setMemPoint(member.getMemPoint() + 2000);
			bis.updateBuy(member);
			member.setMemPoint(member.getMemPoint() - 2000);
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("memList", memList);
		map.put("record", record);
		
		JsonResolver.view(res, map);
		
		return null;
	}

}
