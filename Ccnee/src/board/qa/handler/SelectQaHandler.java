package board.qa.handler;

import java.text.DecimalFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.qa.service.IQaService;
import board.qa.service.QaServiceImpl;
import common.handler.CommandHandler;
import common.util.JsonResolver;
import vo.BoardVO;

public class SelectQaHandler implements CommandHandler {

	private static String VIEW_PAGE = "/views/board/qa/select.jsp";
	
	private IQaService iqs = QaServiceImpl.getInstance();
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equals("GET")) {
			return VIEW_PAGE;
		}
		
		int boardNo = Integer.parseInt(req.getParameter("boardNo"));
		
		BoardVO boardVO = iqs.getBoard(boardNo);
		
		if(req.getParameter("from") == null) {	// 상세 페이지
			//조회수
			iqs.updateCnt(boardNo);
			
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
