package board.report.handler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.report.service.IReportService;
import board.report.service.ReportServiceImpl;
import common.handler.CommandHandler;
import common.util.JsonResolver;
import vo.BoardVO;
import vo.Inven_ItemVO;
import vo.MemberVO;

public class InsertReportHandler implements CommandHandler{

	private static final String VIEW_PAGE = "/views/board/report/insert.jsp";
	private IReportService rs = ReportServiceImpl.getInstance();
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		if(req.getMethod().equals("GET")) {
			
//			String invenCode = req.getParameter("invenCode");
//			
//			try {
//				List<String> teamMemList = new ArrayList<String>(); 
//				teamMemList = rs.getMember(invenCode);
//				
//				JsonResolver.view(res, teamMemList);
//				
//			}catch (SQLException e) {
//				res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//				e.printStackTrace();
//				throw e;
//			}
			
			return null;
			
		}else if(req.getMethod().equals("POST")) {
			
			BoardVO bv = new BoardVO();
			String[] toMemIds = req.getParameterValues("toMemId");
			String ToMemId =null;
			for(String mid : toMemIds){
				ToMemId = mid;
				System.out.println(ToMemId);
			}       
			
//			신고회원 신고횟수+1
			int cnt1 = rs.updateReportCnt(ToMemId);
			
//			신고글 내용+신고당한사람
			String boardContent = "신고당한 회원 : " + ToMemId +"<br><br>"+ req.getParameter("boardContent");
			
//			bv.setMemNo(req.getParameter("memNo"));//신고한 회원
			bv.setMemNo(req.getParameter("memNo"));//신고한 회원
			bv.setBoardContent(boardContent);
			bv.setBoardTitle("신고글");
			bv.setBoardGrNo(4);
			
			System.out.println(bv.getMemNo());
			System.out.println(bv.getBoardContent());
			System.out.println(bv.getBoardTitle());
			System.out.println(bv.getBoardGrNo());
			int cnt2 = rs.insertReport(bv);
			
			
		}
		return null;
	
	}
}
