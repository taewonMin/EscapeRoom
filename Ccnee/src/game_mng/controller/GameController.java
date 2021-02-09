package game_mng.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import game_mng.inven_item.service.IInvenService;
import game_mng.inven_item.service.InvenServiceImpl;
import game_mng.playtime.service.PlaytimeService;
import game_mng.playtime.service.PlaytimeServiceImpl;
import game_mng.team.service.ITeamService;
import game_mng.team.service.TeamServiceImpl;
import vo.InvenVO;
import vo.PlaytimeVO;
import vo.TeamVO;

@WebServlet("/gameMain")
@ServerEndpoint("/gameChat")
public class GameController extends HttpServlet {
	
	// 접속한 유저 Map
	private static Map<Session, String> users = Collections.synchronizedMap(new HashMap<>());
	// 레디중인 유저 Set
	private static Set<String> ready = Collections.synchronizedSet(new HashSet<>());
	// team_mem 테이블을 위한 memNo 저장 Map
	private static Map<Session, String> memNoMap = Collections.synchronizedMap(new HashMap<>());
	// 게임 시작 체크
	private static boolean start = false;
	// 게임 시작 시간
	private static long time = 0; 
	// 게임중인 팀의 코드 번호
	private static String teamCode = "";
	// 게임 인벤토리 생성
	private static String invenCode = "";
	
	// 현재 접속중인 유저의 수를 반환
	public static synchronized int getUserSize() {
		return users.size();
	}
	
	// 현재 게임중인 유저 ID 반환
	public static Set<String> getUserIdList(){
		return ready;
	}
	
	// 게임 진행 상태 반환
	public static synchronized boolean getGameStatus() {
		return start;
	}
	
	// 게임 시작시간 가져오기
	public static long getStartTime() {
		return time;
	}
	
	// 게임중인 팀코드 가져오기
	public static String getTeamCode() {
		return teamCode;
	}
	
	// WebSocket으로 브라우저가 접속하면 요청되는 함수
	@OnOpen
	public void onOpen(Session session){
//		String requestParams = session.getRequestURI().toString().split("?")[1];
		String params = session.getRequestURI().toString();
		String userName = params.split("&")[0].split("=")[1];
		String memNo = params.split("&")[1].split("=")[1];
		
		users.put(session, userName);
		memNoMap.put(session, memNo);
		
		System.out.println(session);
		System.out.println(userName + " connect");
		
		sendNotice(userName + "님이 입장하셨습니다. (" + users.size() + "/4)" + "(#System Message#)" + getUserList());
	}
	
	public static void sendNotice(String message){
		synchronized (users) {
			Iterator<Session> it = users.keySet().iterator();
			while(it.hasNext()){
				Session currentSession = it.next();
				try {
					currentSession.getBasicRemote().sendText(message);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	// WebSocket으로 메시지가 오면 요청되는 함수
	@OnMessage
	public void onMsg(String message, Session session) throws IOException{
		String userName = users.get(session);
		System.out.println(userName + " : " + message);
		synchronized (users) {
			Iterator<Session> it = users.keySet().iterator();
			while(it.hasNext()){
				Session currentSession = it.next();
				if(!currentSession.equals(session)){
					currentSession.getBasicRemote().sendText(userName + "(#User Message#)" + message);
				}
			}
		}
		
	}
	
	// WebSocket과 브라우저가 접속이 끊기면 요청되는 함수
	@OnClose
	public void onClose(Session session) {
		String userName = users.get(session);
		users.remove(session);
		memNoMap.remove(session);
		ready.remove(userName);
		System.out.println(userName + " 연결종료..");
		System.out.println(ready.toString());
		
		if(users.size()==0) {
			start = false;
		}
		
		sendNotice(userName + "님이 퇴장하셨습니다. (" + users.size() + "/4)" + "(#System Message#)" + getUserList());
	}
	
	// 접속중인 유저의 목록을 가져오는 메서드
	public String getUserList() {
		String userList = "";
		
		Iterator<Session> user = users.keySet().iterator();
		while(user.hasNext()) {
			String userId = users.get(user.next());
			String status = "";
			if(start) {
				status = ready.contains(userId) ? "online" : "";
			}else {
				status = ready.contains(userId) ? "ready" : "";
			}
			userList +=  userId + ":" + status + "/";
		}
		return userList;
	}
	
///////////////////////////////////////////// 방 입장 인원 관리 ////////////////////////////////	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String memId = req.getParameter("memId");
		
		// 유저 준비상태 확인
		setReady(memId);
		
		if(ready.size() == 4) {	// 4명이 전부 준비완료되면
			boolean start = "true".equals(req.getParameter("gameStart")) ? true : false;
			
			if(start) { // 게임시작
				insertDB(req, resp);
				gameStart();
			}else {	// cancel 버튼 비활성화
				resp.setContentType("text/html; charset=utf-8");
				PrintWriter out = resp.getWriter();
				
				System.out.println("버튼 비활성화");
				out.print("stopBtn");
			}
		}
	}
	
	public void setReady(String memId) {
		if(memId != null) {
			String readyMsg = memId + "(#ReadyStatus Message#)";
			if(ready.contains(memId)) {
				ready.remove(memId);
			}else {
				ready.add(memId);
				readyMsg += "ready";
			}
			sendNotice(readyMsg);
			System.out.println("준비완료 : " + ready.toString());
		}
	}
	
	public void gameStart() {
		start = true;
		// 카운트 다운
		for(int i=5; i>0; i--) {
			if(i==5) {
				sendNotice("팀원이 모두 모였으므로 " + i + "초 후 게임을 시작합니다.(#System Message#)");
			}else {
				sendNotice("게임 시작까지 " + i + "초 전..(#System Message#)");
			}
			System.out.println(i + "초후 시작..(#System Message#)");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		sendNotice("방 입장 중...(#System Message#)" + getUserList());
		
		sendNotice("(#GameStatus Message#)invenCode="+invenCode);
		
		time = System.currentTimeMillis();
		sendNotice("(#GameStatus Message#)startTime="+time);
		
	}
	
	
///////////////////////////// DB 데이터 삽입 ////////////////////////////////////
	private IInvenService iis = InvenServiceImpl.getInstance();
	private ITeamService its = TeamServiceImpl.getInstance();
	
	private void insertDB(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		int gameNo = Integer.parseInt(req.getParameter("gameNo"));
		
		try {
			// 인벤토리 코드 생성
			invenCode = iis.createInvenCode();
			
			// 인벤토리 생성
			InvenVO iv = new InvenVO();
			iv.setGameNo(gameNo);
			iv.setInvenCode(invenCode);
			
			iis.insertInven(iv);
			
			// 팀 코드 생성
			teamCode = its.createTeamCode();
			
			// 팀 생성
			TeamVO tv = new TeamVO();
			tv.setGameNo(gameNo);
			tv.setInvenCode(invenCode);
			tv.setTeamCode(teamCode);
			
			its.insertTeam(tv);
			
			// 팀원 생성
			HashMap<String, Object> teamInfo = new HashMap<>();
			teamInfo.put("teamCode", teamCode);
			
			List<String> memNoList = new ArrayList<String>();
			Iterator<Session> it = memNoMap.keySet().iterator();
			while(it.hasNext()) {
				memNoList.add(memNoMap.get(it.next()));
			}
			teamInfo.put("memNoList", memNoList);
			
			its.insertTeamMember(teamInfo);
			
		}catch(SQLException e) {
			res.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
	}
	
}
