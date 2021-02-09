package member.handler;

import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import common.handler.CommandHandler;
import common.util.JsonResolver;
import member.service.IMemberService;
import member.service.MemberServiceImpl;
import vo.MemberVO;

public class IdFind_goHandler implements CommandHandler {
	private static final String VIEW_PAGE = "/views/member/idFind_go.jsp";// jsp( 웹페이지)를 webinf밑에다가 널거임

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
		if (req.getMethod().equals("GET")) {
			return VIEW_PAGE;
		} else if (req.getMethod().equals("POST")) {
		IMemberService memberService = MemberServiceImpl.getInstance();
			
			
		ObjectMapper mapper = new ObjectMapper();
		MemberVO mv = mapper.readValue(req.getReader(), MemberVO.class);
		
		res.setContentType("text/plain;charset=utf-8");
		PrintWriter out = res.getWriter();
		
		try {
			  MemberVO member = memberService.findNameAndEmail(mv.toMemberVO());
			  JsonResolver.view(res, member);
			  out.println("SUCCESS");
		}catch (Exception e) {
			  out.print("FAIL");
			  e.printStackTrace();
		}
		finally {
		   if(out!=null) {
		      out.close();
		   }
		}
		return null;
	
		}
			return null;
	}
}
