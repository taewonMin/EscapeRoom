package board.qa.handler;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.qa.service.IQaService;
import board.qa.service.QaServiceImpl;
import common.handler.CommandHandler;

public class DeleteQaHandler implements CommandHandler {
	
	private IQaService iqs = QaServiceImpl.getInstance();
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return true;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		try {
			int cnt = iqs.deleteQa(Integer.parseInt(req.getParameter("boardNo")));
		}catch(SQLException e) {
			res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		
		return null;
	}

}
