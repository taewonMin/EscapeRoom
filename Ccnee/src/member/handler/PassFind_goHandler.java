package member.handler;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import common.handler.CommandHandler;
import common.util.JsonResolver;
import member.service.IMemberService;
import member.service.MemberServiceImpl;
import vo.MemberVO;

public class PassFind_goHandler implements CommandHandler {
	private static final String VIEW_PAGE = "/views/member/passFind_go.jsp";// jsp( 웹페이지)를 webinf밑에다가 널거임

	
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
		    	
		    	MemberVO member = memberService.findIdAndTel(mv.toMemberVO());
		    	if(member!=null) {
		    		String memEmail = member.getMemEmail();
		    		String memId = member.getMemId();
		    		
		    		PassEmail passEmail = new PassEmail();
		    		
		    		passEmail.sendMail(memId, memEmail);
		    		
		    	} 
		    	JsonResolver.view(res, member);
		    		
		    	
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
