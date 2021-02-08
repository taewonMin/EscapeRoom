package common.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandHandler {
	/**
	 * 해당 화면에 대해 포워드 및 리다이렉트 여부 결정하기 위한 메서드
	 * @param req
	 * @return
	 */
	public boolean isRedirect(HttpServletRequest req);
	
	/**
	 * 요청한 내용을 실제 처리하는 메서드
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception;
}
