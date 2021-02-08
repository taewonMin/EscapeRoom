package board.event.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibatis.sqlmap.engine.type.IntegerTypeHandler;

import board.event.service.EventService;
import board.event.service.EventServiceImpl;
import common.file.service.AtchFileService;
import common.file.service.AtchFileServiceImpl;
import common.handler.CommandHandler;
import vo.BoardFileVO;
import vo.BoardVO;

public class SelectEventBoardHandler implements CommandHandler{
	
	private static final String VIEW_PAGE = "/views/board/event/selectEvent.jsp";
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		EventService eventService = EventServiceImpl.getInstance();
		
		AtchFileService atchFileService = AtchFileServiceImpl.getInstance();
		
		int boardNo = Integer.parseInt(req.getParameter("boardNo"));
		
		eventService.cntEventBoard(boardNo);
		
		BoardVO boardVO = eventService.eventSearch(boardNo);
		
		BoardFileVO boardFileVO = atchFileService.eventFileSearch(boardNo);
		
		System.out.println(boardFileVO);
		
		req.setAttribute("boardVO", boardVO);
		req.setAttribute("boardFileVO", boardFileVO);
		
		
		return VIEW_PAGE;
	}

}
