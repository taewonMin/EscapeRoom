package common.handler;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.event.service.EventService;
import board.event.service.EventServiceImpl;
import board.item.service.ItemService;
import board.item.service.ItemServiceImpl;
import common.file.service.AtchFileService;
import common.file.service.AtchFileServiceImpl;
import game_mng.playtime.service.PlaytimeService;
import game_mng.playtime.service.PlaytimeServiceImpl;
import member.service.IMemberService;
import member.service.MemberServiceImpl;
import vo.BoardFileVO;
import vo.BoardVO;
import vo.ItemVO;
import vo.PlaytimeVO;


public class MainHandler implements CommandHandler {

	private static final String VIEW_PAGE="/views/main.jsp";
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		PlaytimeService playtimeService = PlaytimeServiceImpl.getInstance();
		
		EventService eventService = EventServiceImpl.getInstance();
		
		ItemService itemService = ItemServiceImpl.getInstance();
		// 플레이기록 상위 10번까지 조회
		List<PlaytimeVO> playtimeList = playtimeService.displayAll();
		
		req.setAttribute("playtimeList", playtimeList);
		
		String teamCode = "";
		
		List<String> memList = new ArrayList<>();
		for(int i=0; i<playtimeList.size(); i++) {
			String memIdList = "";
			
			teamCode = playtimeList.get(i).getTeamCode();
			
			List<String> playMemIdList = playtimeService.getPlayMemId(teamCode);
			
			for(int j=0; j<playMemIdList.size(); j++) {
				memIdList += playMemIdList.get(j);
			}
			
			memList.add(memIdList);
			
		}
		// 이벤트 게시판 내용 전체조회
		List<BoardVO> boardList = eventService.getEventList();
		
		// 아이템 리스트 및 구매 조회
		List<ItemVO> itemList = itemService.getItemList();
		
		// 상세
		
		req.setAttribute("memList", memList);
		req.setAttribute("boardList", boardList);
		req.setAttribute("itemList", itemList);
		
		return VIEW_PAGE;
	}

}
