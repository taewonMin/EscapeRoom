package board.notice.handler;

import java.text.DecimalFormat;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.notice.service.NoticeService;
import board.notice.service.NoticeServiceImpl;
import common.handler.CommandHandler;
import common.util.JsonResolver;
import vo.BoardVO;
import vo.SearchVO;

public class SelectNoticeBoardHandler implements CommandHandler {
	
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		NoticeService noticeService = NoticeServiceImpl.getInstance();
		
		//getParameter하면 String으로 받아오니까 parseInt해서 int로 형변환
		int boardNo = Integer.parseInt(req.getParameter("boardNo"));
		
		BoardVO boardVO = noticeService.getSearchNoticeBoard(boardNo);
		System.out.println(boardVO);
		if(req.getParameter("from")==null) {
			
			//조회수
			noticeService.updateCntBoard(boardNo);
			// 데이터 형식 수정
			DecimalFormat df = new DecimalFormat("###000");
			String boardNum = "NO." + df.format(boardVO.getBoardNo());
			String content = boardVO.getBoardContent().replaceAll(System.lineSeparator(), "<br>");
			
			boardVO.setBoardNum(boardNum);
			boardVO.setBoardContent(content);
		}
		
		JsonResolver.view(res, boardVO);
		
		return null;
	}

}
