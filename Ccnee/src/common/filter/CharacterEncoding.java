package common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncoding implements Filter {
	
	private String encoding;	// 인코딩 정보
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc)
			throws IOException, ServletException {
		System.out.println("request 인코딩 설정 : " + encoding);
		
		// 인코딩 설정
		req.setCharacterEncoding(this.encoding);
		resp.setCharacterEncoding(this.encoding);
		
		// 로그인 필터 체크
		req.setAttribute("loginCheck", "N");
		
		fc.doFilter(req, resp);
		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		if(config.getInitParameter("encoding") == null) {
			this.encoding = "UTF-8";
		}else {
			this.encoding = config.getInitParameter("encoding");
		}
	}
}
