package board.qa.handler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.qa.service.IQaService;
import board.qa.service.QaServiceImpl;
import common.handler.CommandHandler;
import common.util.JsonResolver;
import member.service.IMemberService;
import member.service.MemberServiceImpl;
import vo.BoardVO;
import vo.MemberVO;
import vo.PagingVO;
import vo.SearchVO;

public class ListQaHandler implements CommandHandler {

	private static String VIEW_PAGE = "/views/board/qa/list.jsp";
	
	private IQaService iqs = QaServiceImpl.getInstance();
	
	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equals("GET")) {
			return VIEW_PAGE;
		}else if(req.getMethod().equals("POST")) {
			
			SearchVO sv = new SearchVO();
			
			Cookie[] cookies = req.getCookies();
			
			int pageNo = req.getParameter("pageNo")==null ? 1 : Integer.parseInt(req.getParameter("pageNo"));
			for(Cookie c : cookies) {
				if("boardGrNo".equals(c.getName())) {
					sv.setBoardGrNo(c.getValue());
				}
			}
			
			try {
				// 검색한 경우
				String criteria = req.getParameter("criteria");
				String search = req.getParameter("search");
				if(criteria != null) {
					sv.setCriteria(criteria);
					sv.setSearch(search);
					
				}
				
				int totalCount = iqs.getBoardAllCount(sv);
				
				// 페이징 처리
				sv.setTotalCount(totalCount);
				sv.setCurrentPageNo(pageNo);
				sv.setPageSize(5);
				
				List<BoardVO> qaList = new ArrayList<>();
				qaList = iqs.displayAllList(sv);
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("qaList", qaList);
				map.put("searchVO", sv);
				
				JsonResolver.view(res, map);
				
			}catch(SQLException e) {
				res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				e.printStackTrace();
			}
		}
		
		return null;
	}

}
