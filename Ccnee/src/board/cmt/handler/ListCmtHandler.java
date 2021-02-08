package board.cmt.handler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.cmt.service.ICmtService;
import common.handler.CommandHandler;
import common.util.JsonResolver;
import vo.CmtVO;

public class ListCmtHandler implements CommandHandler{

	ICmtService CmtService = board.cmt.service.CmtService.getInstance();
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		int bno = Integer.parseInt(req.getParameter("bno"));
		
		try {
			List<CmtVO> cmtList = new ArrayList<CmtVO>();
			cmtList = CmtService.listCmt(bno);
			
			JsonResolver.view(res, cmtList);
			
		}catch (SQLException e) {
			res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
			throw e;
		}
		
		
		return null;
	}

}
