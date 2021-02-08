package board.qa.handler;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.qa.service.IQaService;
import board.qa.service.QaServiceImpl;
import common.handler.CommandHandler;
import vo.BoardVO;

public class UpdateQaHandler implements CommandHandler {

	private IQaService iqs = QaServiceImpl.getInstance();
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		BoardVO bv = new BoardVO();
		
		bv.setBoardTitle(req.getParameter("boardTitle"));
		bv.setBoardContent(req.getParameter("boardContent"));
		bv.setBoardNo(Integer.parseInt(req.getParameter("boardNo")));
		
		try {
			iqs.updateQa(bv);
		}catch(SQLException e) {
			res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return null;
	}

}
