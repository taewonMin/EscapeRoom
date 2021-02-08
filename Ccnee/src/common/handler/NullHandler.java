package common.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NullHandler implements CommandHandler{

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		res.sendError(HttpServletResponse.SC_NOT_FOUND); // 404 에러
		
		return null;
	}

}
