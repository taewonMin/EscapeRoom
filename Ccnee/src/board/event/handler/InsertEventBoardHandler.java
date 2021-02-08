package board.event.handler;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import board.event.service.EventService;
import board.event.service.EventServiceImpl;
import common.file.service.AtchFileService;
import common.file.service.AtchFileServiceImpl;
import common.handler.CommandHandler;
import common.util.FileUploadRequestWrapper;
import vo.BoardFileVO;
import vo.BoardVO;

public class InsertEventBoardHandler implements CommandHandler{
	
	private static final String VIEW_PAGE = "/views/board/event/insertEvent.jsp";
	
	private AtchFileService boardFileService = AtchFileServiceImpl.getInstance();
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		
		if(req.getMethod().equals("GET")) {
			return false;
		}else {
			return true;
		}
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equals("GET")) {
			return VIEW_PAGE;
		}else if(req.getMethod().equals("POST")) {
			
			
			String boardTitle = req.getParameter("boardTitle");
			String boardContent = req.getParameter("boardContent");
			
			EventService eventService = EventServiceImpl.getInstance();
			
			// boardSeq 다음꺼 번호 가져오기
			int boardNo = eventService.getEventSeq();
			
			BoardVO bv = new BoardVO();
			bv.setBoardTitle(boardTitle);
			bv.setBoardContent(boardContent);
			bv.setBoardNo(boardNo);
			
			FileItem item = ((FileUploadRequestWrapper)req).getFileItem("boardFile");
			
			
			// eventBoard 정보 저장
			int cnt = eventService.insertEvent(bv);
			
			// eventBoard 보드번호 최대값 조회
//			int boardNoChk = eventService.getSearchEvnet(bv);
			
			
			BoardFileVO boardFileVO = new BoardFileVO();
			
			if(item != null && !item.getName().equals("")) {
					boardFileVO = boardFileService.saveAtchFile(item, boardNo);
			}
			
			
			String msg = "";
			if(cnt > 0) {
				msg = "성공";
			}else {
				msg = "실패";
			}
			
			
			String redirectUrl = req.getContextPath()
					+ "/views/board/event/listEvent.do?msg=" + URLEncoder.encode(msg, "utf-8");
			
			return redirectUrl;
		}
		
		
		return null;
	}

}
