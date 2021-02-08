package board.report.handler;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.report.service.IReportService;
import board.report.service.ReportServiceImpl;
import common.handler.CommandHandler;

public class DeleteReportHandler implements CommandHandler{

	private IReportService irs = ReportServiceImpl.getInstance();
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return true;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {

		int boardNo = Integer.parseInt(req.getParameter("boardNo"));
		
		int cnt = irs.deleteReport(boardNo);
		
		String msg = "";
		if(cnt > 0) {
			msg = "성공";
		}else {
			msg = "실패";
		}
		String redirectUrl = req.getContextPath() + "/report/list.do?msg="+URLEncoder.encode(msg,"UTF-8")+"&boardGrNo="+req.getParameter("boardGrNo");
		return redirectUrl;

	}

}
