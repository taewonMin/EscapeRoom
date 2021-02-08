package board.event.handler;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.event.service.EventService;
import board.event.service.EventServiceImpl;
import common.file.service.AtchFileService;
import common.file.service.AtchFileServiceImpl;
import common.handler.CommandHandler;

public class DeleteEventBoardHandler implements CommandHandler{
	
	private EventService eventService = EventServiceImpl.getInstance();
	
	private AtchFileService atchFileService = AtchFileServiceImpl.getInstance();
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		
		return true;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		
		int deleteFile = atchFileService.deleteFile(Integer.parseInt(req.getParameter("boardNo")));
		
		int cnt = eventService.deleteEvent(Integer.parseInt(req.getParameter("boardNo")));
		
		
		String msg = "";
		if(cnt > 0) {
			msg = "성공";
		}else {
			msg = "실패";
		}
		
		String redirectUrl = req.getContextPath() + "/views/board/event/listEvent.do?msg="
				+ URLEncoder.encode(msg, "utf-8");
		
		return redirectUrl;
	}

}
