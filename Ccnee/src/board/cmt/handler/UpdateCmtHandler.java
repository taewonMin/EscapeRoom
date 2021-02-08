package board.cmt.handler;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import board.cmt.service.ICmtService;
import vo.CmtVO;


public class UpdateCmtHandler implements common.handler.CommandHandler{

	ICmtService CmtService = board.cmt.service.CmtService.getInstance();
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		CmtVO cv = mapper.readValue(req.getReader(), CmtVO.class);
		
		res.setContentType("text/plain;charset=utf-8");
		PrintWriter out = res.getWriter();
		
		CmtService.updateCmt(cv.toCmtVO());
		out.println("SUCCESS");
		
		
		
		return null;
	}

}
