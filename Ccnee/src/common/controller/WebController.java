package common.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import common.handler.CommandHandler;
import common.handler.NullHandler;
import common.util.FileUploadRequestWrapper;

public class WebController extends HttpServlet{
	private Map<String, CommandHandler> cmdHandlerMap = new HashMap<String, CommandHandler>();
	
	// 메모리 임계 크기
	private static final int MAX_THRESHOLD = 1024 * 1024 * 3;
		
	// 파일 1개당 최대 크기
	private static final long MAX_FILE_SIZE = 1024 * 1024 * 40;
	
	// 요청파일 최대크기
	private static final long MAX_REQUEST_SIZE = 1024 * 1024 * 40;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		String configFilePath = config.getInitParameter("handler-config");
		
		Properties handlerProp = new Properties();
		
		String configFileRealPath = config.getServletContext().getRealPath(configFilePath);
		
		FileReader fr; 
		
		try {
			fr = new FileReader(configFileRealPath);
			handlerProp.load(fr); 
			
		}catch(IOException e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		
		for(Object key : handlerProp.keySet()) {
			String reqUrl = (String) key;
			
			try {
				Class<?> klass = Class.forName(handlerProp.getProperty(reqUrl)); //key값 넣고 value뽑아냄
				CommandHandler handlerInstance = (CommandHandler) klass.newInstance(); 
				
				cmdHandlerMap.put(reqUrl, handlerInstance); 
				
			} catch(Exception e) {
				e.printStackTrace();
				throw new ServletException(e);
			}
		}
		
		Set<Map.Entry<String, CommandHandler>> entrySet = cmdHandlerMap.entrySet();
		for(Map.Entry<String, CommandHandler> entry : entrySet) {
			System.out.println(entry.getKey() + " => " + entry.getValue()+"주소확인");
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			FileUploadRequestWrapper requestWrapper = new FileUploadRequestWrapper(req, MAX_THRESHOLD, MAX_FILE_SIZE, MAX_REQUEST_SIZE, null);
			process(requestWrapper, resp);
		}catch(FileUploadException e) {
			e.printStackTrace();
			throw new ServletException(e);
		}

	}

	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if("Y".equals(req.getAttribute("loginCheck"))) {
			return;
		}
		
		String reqURI = req.getRequestURI(); 
		
		if(reqURI.indexOf(req.getContextPath()) == 0) {
			reqURI = reqURI.substring(req.getContextPath().length()); 
		}
		
		System.out.println("reqURI : " + reqURI);
		System.out.println("cmdHandlerMap : " + cmdHandlerMap);
		
		CommandHandler handler = cmdHandlerMap.get(reqURI);
									
		if(handler == null) {
			handler = new NullHandler();
		}
		
		String viewPage = "";
		try {
			viewPage = handler.process(req, resp);
		}catch(Throwable e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
		
		System.out.println("viewPage : " + viewPage);
		
		if(viewPage != null) { 
			if(handler.isRedirect(req)) { 
				resp.sendRedirect(viewPage);
			} else {
				RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
				dispatcher.forward(req, resp);
			}
		}
		
	}
}
