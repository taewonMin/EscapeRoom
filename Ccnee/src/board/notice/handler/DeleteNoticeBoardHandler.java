package board.notice.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.notice.service.NoticeService;
import board.notice.service.NoticeServiceImpl;
import common.handler.CommandHandler;

public class DeleteNoticeBoardHandler implements CommandHandler {

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return true;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		int boardNo = Integer.parseInt(req.getParameter("boardNo"));
		NoticeService noticeService = NoticeServiceImpl.getInstance();
		
		noticeService.deleteNoticeBoard(boardNo);
	
		return null;
		
	}

}
