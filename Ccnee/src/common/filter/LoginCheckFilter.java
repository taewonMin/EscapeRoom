package common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCheckFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		
		loginCheck(request, (HttpServletResponse) res);
		
		fc.doFilter(request, res);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}
	
	private void loginCheck(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String memId = (String) req.getSession().getAttribute("memId");
		if(memId == null) {
			// 로그인 페이지로
			req.setAttribute("loginCheck", "Y");
			res.sendRedirect("/CCNEE/views/loading.html");
		}
	}
}
