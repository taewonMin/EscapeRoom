package board.notice.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.notice.service.NoticeService;
import board.notice.service.NoticeServiceImpl;
import common.handler.CommandHandler;
import vo.BoardVO;

public class UpdateNoticeBoardHandler implements CommandHandler {
	
	private NoticeService noticeService = NoticeServiceImpl.getInstance();
	
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		if(req.getMethod().equals("GET")) {
			return false;
		} else {
			return true;
			
		}
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
			
			String boardTitle = req.getParameter("boardTitle");
			String boardContent = req.getParameter("boardContent");
			int boardNo = Integer.valueOf(req.getParameter("boardNo"));
			
			BoardVO boardVO = new BoardVO();
			boardVO.setBoardTitle(boardTitle);
			boardVO.setBoardContent(boardContent);
			boardVO.setBoardNo(boardNo);
			
			noticeService.updateNoticeBoard(boardVO);
			
		
			
		return null;
	}

}
