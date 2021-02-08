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

public class UpdateEventBoardHandler implements CommandHandler{
	
	private static final String VIEW_PAGE = "/views/board/event/updateEvent.jsp";
	
	private EventService eventService = EventServiceImpl.getInstance();
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		if(req.getMethod().equals("GEt")) {
			return false;
		}else {
			
			return true;
		}
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equals("GET")) {
			int  boardNo = Integer.parseInt(req.getParameter("boardNo"));
			
			BoardVO boardVO = new BoardVO();
			boardVO.setBoardNo(boardNo);
			
			boardVO = eventService.eventSearch(boardNo);
			
			req.setAttribute("boardVO", boardVO);
			
			return VIEW_PAGE;
		}else if(req.getMethod().equals("POST")) {
			
			String boardTitle = req.getParameter("boardTitle");
			String boardContent = req.getParameter("boardContent");
			int boardNo = Integer.parseInt(req.getParameter("boardNo"));
			
			BoardVO boardVO = new BoardVO();
			boardVO.setBoardTitle(boardTitle);
			boardVO.setBoardContent(boardContent);
			boardVO.setBoardNo(boardNo);
			
			System.out.println(boardNo);
			
			// 관리자가 입력한 게시판 정보로 게시판 수정
			int cnt = eventService.updateEvent(boardVO);
			
			AtchFileService boardFileService = AtchFileServiceImpl.getInstance();
			
			FileItem item = ((FileUploadRequestWrapper)req).getFileItem("boardFile");
			// 수정한 해당 게시판 번호로 첨부파일 이미지 있으면 수정
			BoardFileVO boardFileVO = new BoardFileVO();
			
			if(item != null && !item.getName().equals("")) {
					boardFileVO = boardFileService.uploadSaveAtchFile(item, boardNo);
			}
			
			String msg = "";
			
			if(cnt > 0) {
				msg = "성공";
			}else {
				msg = "실패";
			}
			
			String redirectURL = req.getContextPath() + "/views/board/event/listEvent.do?msg=" + URLEncoder.encode(msg, "UTF-8");
			
			return redirectURL;
		}
		
		return VIEW_PAGE;
	}

}
