package board.cmt.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.cmt.service.CmtService;
import board.cmt.service.ICmtService;

public class DeleteCmtHandler implements common.handler.CommandHandler{

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		int cmtNo = Integer.parseInt(req.getParameter("cmtNo"));
		
		ICmtService cmtService = CmtService.getInstance();
		
		int cnt = cmtService.deleteCmt(cmtNo);
	
		return null;
		
		
	}

}
