package board.qa.handler;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import board.qa.service.IQaService;
import board.qa.service.QaServiceImpl;
import common.handler.CommandHandler;
import vo.BoardVO;

public class InsertQaHandler implements CommandHandler {

	private static String VIEW_PAGE = "/views/board/qa/insert.jsp";
	
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
		
		BoardVO bv = new BoardVO();
		
		bv.setBoardTitle(req.getParameter("boardTitle"));
		bv.setBoardContent(req.getParameter("boardContent"));
		bv.setMemNo((String)req.getSession().getAttribute("memNo"));
		bv.setBoardGrNo(Integer.parseInt(req.getParameter("boardGrNo")));
		
		try {
			iqs.insertQa(bv);
		}catch (SQLException e) {
			res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return null;
	}

}
