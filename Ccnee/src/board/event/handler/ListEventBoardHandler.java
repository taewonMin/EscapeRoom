package board.event.handler;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.event.service.EventService;
import board.event.service.EventServiceImpl;
import board.notice.service.NoticeService;
import board.notice.service.NoticeServiceImpl;
import common.file.service.AtchFileService;
import common.file.service.AtchFileServiceImpl;
import common.handler.CommandHandler;
import game_mng.playtime.service.PlaytimeService;
import game_mng.playtime.service.PlaytimeServiceImpl;
import vo.BoardFileVO;
import vo.BoardVO;
import vo.PagingVO;
import vo.PlaytimeVO;

public class ListEventBoardHandler implements CommandHandler{
	
	private static final String VIEW_PAGE = "/views/board/event/newListTest.jsp";
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		EventService eventService = EventServiceImpl.getInstance();
		
		AtchFileService atchFileService = AtchFileServiceImpl.getInstance();
		
		int pageNo = req.getParameter("pageNo") == null ? 
				1 : Integer.parseInt(req.getParameter("pageNo"));
		
		PagingVO pagingVO = new PagingVO();
		int totalCount = eventService.getEventAllCount();
		pagingVO.setTotalCount(totalCount);
		pagingVO.setCurrentPageNo(pageNo);
		pagingVO.setCountPerPage(4);
		pagingVO.setPageSize(4);
		
		List<BoardVO> eventBoardList = eventService.displayAllEvent(pagingVO);
		
		// 게시판 그룹넘버가 1(이벤트)에 해당하는 게시판 그룹번호 가져오기
		int boardGrNo = eventService.getBoardNo();
			
		List<BoardFileVO> boardFileList = atchFileService.getAtchFileList(boardGrNo);
		
		req.setAttribute("boardFileList", boardFileList);
		req.setAttribute("eventBoardList", eventBoardList);
		req.setAttribute("pagingVO", pagingVO);
		
		return VIEW_PAGE;
	}

}
