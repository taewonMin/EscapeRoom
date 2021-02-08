package board.report.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.report.service.IReportService;
import board.report.service.ReportServiceImpl;
import common.handler.CommandHandler;
import vo.BoardVO;

public class SelectReportHandler implements CommandHandler{

	private static final String VIEW_PAGE = "/views/board/report/select.jsp";
	private IReportService ReportService = ReportServiceImpl.getInstance();
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		int boardNo = Integer.parseInt(req.getParameter("boardNo"));
		int no = Integer.parseInt(req.getParameter("no"));
		
		BoardVO bv = ReportService.getReport(boardNo);
		
		req.setAttribute("BoardVO", bv);
		req.setAttribute("no", no);
		
		return VIEW_PAGE;
	}

}
