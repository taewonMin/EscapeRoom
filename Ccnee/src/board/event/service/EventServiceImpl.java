package board.event.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import board.event.dao.EventDao;
import board.event.dao.EventDaoImpl;
import common.util.SqlMapClientFactory;
import vo.BoardVO;
import vo.PagingVO;

public class EventServiceImpl implements EventService{
	
	private static EventService eventService;
	private EventDao eventDao;
	private SqlMapClient smc;
	
	public EventServiceImpl() {
		eventDao = EventDaoImpl.getInstance();
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static EventService getInstance() {
		if(eventService ==null) {
			eventService = new EventServiceImpl();
		}
		return eventService;
	}
	
	@Override
	public int insertEvent(BoardVO bv) {
		
		int cnt = 0;
		
		try {
			//smc.startTransaction();
			cnt = eventDao.insertEventBoard(smc, bv);
			
			//smc.commitTransaction();
		}catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return cnt;
	}

//	@Override
//	public boolean getBoardNo(int boardNo) {
//		
//		boolean chk = false;
//		
//		try {
//			chk = eventDao.getBoardNo(smc, boardNo);
//		}catch(SQLException e) {
//			throw new RuntimeException("해당 글 제목이 있는지 검색중 예외발생");
//		}
//		
//		return chk;
//	}

	@Override
	public List<BoardVO> displayAllEvent(PagingVO pagingVO) {
		
		List<BoardVO> boardList = Collections.emptyList();
		
		try {
			boardList = eventDao.displayAllEvent(smc, pagingVO);
		}catch(SQLException e) {
			throw new RuntimeException("이벤트 게시판 조회중 예외 발생", e);
		}
		
		return boardList;
	}

	@Override
	public int updateEvent(BoardVO boardVO) {
		
		int cnt = 0;
		
		try {
			cnt = eventDao.updateEvent(smc, boardVO);
		}catch(SQLException e) {
			throw new RuntimeException("이벤트 정보 수정중 예외발생");
		}
		
		return cnt;
	}

	@Override
	public int deleteEvent(int boardNo) {
		
		int cnt = 0;
		
		try {
			cnt = eventDao.deleteEvent(smc, boardNo);
		}catch(SQLException e) {
			throw new RuntimeException("이벤트 게시판 삭제중 예외발생");
		}
		
		return cnt;
	}

	@Override
	public int getSearchEvnet(BoardVO bv) {
		
		int cnt = 0;
		
		try {
			cnt = eventDao.getSearchEvent(smc, bv);
		}catch(SQLException e) {
			throw new RuntimeException("이벤트 게시판 검색중 예외발생");
		}
		
		return cnt;
	}
	
	@Override
	public int getEventAllCount() {
		
		int cnt = 0;
		
		try {
			cnt = eventDao.getEventAllCount(smc);
		}catch(SQLException e) {
			throw new RuntimeException("이벤트 테이블레코드 전체 조회중 예외발생");
		}
		
		return cnt;
	}

	@Override
	public int cntEventBoard(int boardNo) throws SQLException {
		
		return eventDao.cntEventBoard(smc, boardNo);
	}

	@Override
	public int getEventSeq() {
		int cnt = 0;
		
		try {
			cnt = eventDao.getEventSeq(smc);
		}catch(SQLException e) {
			throw new RuntimeException("이벤트 테이블레코드 전체 조회중 예외발생");
		}
		
		return cnt;
	}

	@Override
	public int getBoardNo() {
		
		int cnt = 0;		
		
		try {
			cnt = eventDao.getBoardNo(smc);
		}catch(SQLException e) {
			throw new RuntimeException("게시물번호 가져오는중 예외 발생");
		}
		
		return cnt;
	}

	@Override
	public BoardVO eventSearch(int boardNo) {
		
		BoardVO boardVO = new BoardVO();
		
		try {
			boardVO = eventDao.eventSearch(smc, boardNo);
		}catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("클릭 게시판 검색중 예외발생");
		}
		
		return boardVO;
	}

	@Override
	public List<BoardVO> getEventList() {
		
		List<BoardVO> boardList = Collections.emptyList();
		
		try {
			boardList = eventDao.getEventList();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return boardList;
	}



}
