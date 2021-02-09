package member.handler;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.handler.CommandHandler;
import common.util.JsonResolver;
import member.service.IMemberService;
import member.service.MemberServiceImpl;
import vo.MemberVO;
import vo.SearchVO;

public class ManagementHandler implements CommandHandler {
	
	private static String VIEW_PAGE = "/views/admin/management.jsp";
	private IMemberService memberService = MemberServiceImpl.getInstance();

	@Override
	public boolean isRedirect(HttpServletRequest req) {
		return false;
	}

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equals("GET")) {
			return VIEW_PAGE;
		}
		SearchVO sv = new SearchVO();

		int pageNo = req.getParameter("pageNo")==null ? 1 : Integer.parseInt(req.getParameter("pageNo"));
		System.out.println("핸들러"+pageNo);
		int totalCount = memberService.getMemberAllCount(sv);
		System.out.println("핸들러"+totalCount);
		
		try {
			sv.setTotalCount(totalCount);
			sv.setCurrentPageNo(pageNo);
			sv.setPageSize(5);
			
			List<MemberVO> memberList = memberService.managementList(sv);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("memberList", memberList);
			map.put("searchVO", sv);
			JsonResolver.view(res, map);
			
		} catch (SQLException e) {
			res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		
		return null;
	}

}
