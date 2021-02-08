package board.notice.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.notice.service.NoticeService;
import board.notice.service.NoticeServiceImpl;
import common.handler.CommandHandler;
import vo.BoardVO;

public class InsertNoticeBoardHandler implements CommandHandler {
	
	private static final String VIEW_PAGE = "/views/board/notice/insertNoticeBoard.jsp";
	
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		if(req.getMethod().equals("GET")) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equals("GET")) {
			return VIEW_PAGE;
		} else if (req.getMethod().equals("POST")) {
			
			String boardTitle = req.getParameter("boardTitle");
			String boardContent = req.getParameter("boardContent");
			String memNo = (String)req.getSession().getAttribute("memNo");
			int boardGrNo = Integer.parseInt(req.getParameter("boardGrNo"));
			
			NoticeService noticeService = NoticeServiceImpl.getInstance();
			
			BoardVO boardVO = new BoardVO();
			boardVO.setBoardTitle(boardTitle);
			boardVO.setBoardContent(boardContent);
			boardVO.setMemNo(memNo);
			boardVO.setBoardGrNo(boardGrNo);
			
			int cnt = noticeService.insertNoticeBoard(boardVO);
			
			
			
			String msg = "";
			if(cnt>0) {
				msg = "성공";
			}else {
				msg = "실패";
			}
			System.out.println(msg);
			
		}
			
		return null;
	}

}
