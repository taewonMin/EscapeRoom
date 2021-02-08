package board.notice.handler;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.notice.service.NoticeService;
import board.notice.service.NoticeServiceImpl;
import common.handler.CommandHandler;
import common.util.JsonResolver;
import vo.BoardVO;
import vo.PagingVO;
import vo.SearchVO;

public class ListNoticeBoardHandler implements CommandHandler {

//	private static final String VIEW_PAGE = "/views/board/notice/listNoticeBoard.jsp";
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		//쿠키에서 겟해서 그룹넘버랑 페이지넘버 뽑아내기
		Cookie[] cookies = req.getCookies();
		SearchVO sv = new SearchVO();
		
		int pageNo = req.getParameter("pageNo")==null ? 1 : Integer.parseInt(req.getParameter("pageNo"));
		for(Cookie c : cookies) {
			if("boardGrNo".equals(c.getName())) {
				if(c.getValue().equals("0")) {
					sv.setBoardGrNo("2");
				}else {
					sv.setBoardGrNo(c.getValue());
				}
			}
		}
		
		NoticeService noticeService = NoticeServiceImpl.getInstance();
		int totalCount = noticeService.getAllCountNoticeBoard(sv);
		
		try {
			sv.setTotalCount(totalCount);
			sv.setCurrentPageNo(pageNo);
			sv.setPageSize(5);
			
			List<BoardVO> noticeBoardList = noticeService.getAllNoticeBoard(sv);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("noticeBoardList", noticeBoardList);
			map.put("searchVO", sv);
			System.out.println("핸들러 : "+noticeBoardList);
			
			JsonResolver.view(res, map);

		} catch (SQLException e) {
			res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
//		int pageNo = req.getParameter("pageNo") == null ? 1 : Integer.parseInt(req.getParameter("pageNo"));
//		int totalCount = noticeService.getAllCountNoticeBoard();
//		
//		PagingVO pagingVO = new PagingVO();
//		pagingVO.setTotalCount(totalCount);
//		System.out.println(totalCount);
//		pagingVO.setCurrentPageNo(pageNo);
//		pagingVO.setCountPerPage(5);
//		pagingVO.setPageSize(5);
//		
//		
//		req.setAttribute("noticeBoardList", noticeBoardList);
//		req.setAttribute("pagingVO", pagingVO);
//		
//		return VIEW_PAGE;
		return null;
		
	}
	
}
