package board.report.handler;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.report.service.IReportService;
import board.report.service.ReportServiceImpl;
import common.handler.CommandHandler;
import vo.BoardVO;
import vo.PagingVO;
import vo.SearchVO;

public class ListReportHandler implements CommandHandler{
	private static final String VIEW_PAGE = "/views/board/report/list.jsp";
	private IReportService rs = ReportServiceImpl.getInstance();
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		Cookie[] cookies = req.getCookies();
		int boardGrNo=0;
		SearchVO sv = new SearchVO();
		int pageNo = req.getParameter("pageNo")==null ? 1 : Integer.parseInt(req.getParameter("pageNo"));
		for(Cookie c : cookies) {
			if("boardGrNo".equals(c.getName())) {
				boardGrNo = Integer.parseInt(c.getValue());
				sv.setBoardGrNo(boardGrNo+"");
			}
		}
		
		
		// 페이징 처리
//		int pageNo = req.getParameter("pageNo") == null ? 1 : Integer.parseInt(req.getParameter("pageNo"));
		
		PagingVO pagingVO = new PagingVO();
		int totalCount = rs.getBoardAllCount(sv);
		pagingVO.setTotalCount(totalCount);
		pagingVO.setCurrentPageNo(pageNo);
		pagingVO.setPageSize(5);
		
		List<BoardVO> reportList = rs.listReport(boardGrNo, pagingVO);
		
		req.setAttribute("reportList", reportList);
		req.setAttribute("pagingVO", pagingVO);
		
		return VIEW_PAGE;
	}

}
