package board.event.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.input.BoundedReader;

import com.ibatis.sqlmap.client.SqlMapClient;

import common.util.BaseDao;
import vo.BoardFileVO;
import vo.BoardVO;
import vo.PagingVO;

public class EventDaoImpl extends BaseDao implements EventDao{
	
	private static EventDao eventDao;
	private SqlMapClient smc;
	
	public EventDaoImpl() {
		smc = getSqlMapClient();
	}
	public static EventDao getInstance() {
		if(eventDao == null) {
			eventDao = new EventDaoImpl();
		}
		return eventDao;
	}
	
	
	@Override
	public int insertEventBoard(SqlMapClient smc, BoardVO bv) throws SQLException {
		
		int cnt = 0;
		
		Object obj = smc.insert("event.insertEventBoard", bv);
		if(obj == null) {
			cnt = 1;
		}
		
		return cnt;
	}
	
	@Override
	public int insertEventFile(SqlMapClient smc, BoardFileVO bfv) throws SQLException {
		
		int cnt = 0;
		
		Object obj = smc.insert("event.insertEventFile", bfv);
		if(obj == null) {
			cnt = 1;
		}
		
		return cnt;
	}

//	@Override
//	public boolean getBoardNo(SqlMapClient smc, int boardNo) throws SQLException {
//		
//		boolean chk = false;
//		
//		BoardVO bv = (BoardVO)smc.queryForObject("event.getBoardNo", boardNo);
//		
//		if(bv != null) {
//			chk = true;
//		}
//		
//		return chk;
//	}

	@Override
	public List<BoardVO> displayAllEvent(SqlMapClient smc, PagingVO pagingVO) throws SQLException {
		
		List<BoardVO> eventBoardList = new ArrayList<>();
		
		eventBoardList = smc.queryForList("event.displayAllEvent", pagingVO);
		
		return eventBoardList;
	}

	@Override
	public int updateEvent(SqlMapClient smc, BoardVO boardVO) throws SQLException {
		
		int cnt = 0;
		
		cnt = smc.update("event.updateBoard", boardVO);
		
		return cnt;
	}

	@Override
	public int deleteEvent(SqlMapClient smc, int boardNo) throws SQLException {
		
		int cnt = 0;
		
		cnt = smc.update("event.deleteEventBoard", boardNo);
		
		return cnt;
	}

	@Override
	public int getSearchEvent(SqlMapClient smc, BoardVO bv) throws SQLException {
		
		int cnt = (int) smc.queryForObject("event.getSearchEvent", bv);
			
		return cnt;
	}
	
	@Override
	public int getEventAllCount(SqlMapClient smc) throws SQLException {
		int cnt = (int)smc.queryForObject("event.getEventAllCount");
		
		return cnt;
	}

	@Override
	public int cntEventBoard(SqlMapClient smc, int boardNo) throws SQLException {
		
		int cnt = 0;
		
		cnt = smc.update("event.cntEventBoard", boardNo);
		
		return cnt;
	}
	@Override
	public int getEventSeq(SqlMapClient smc) throws SQLException {
		
		int cnt = (int)smc.queryForObject("event.selectEventSeqNextval");
		
		return cnt;
	}
	@Override
	public int getBoardNo(SqlMapClient smc) throws SQLException {
		
		
		int cnt = (int)smc.queryForObject("event.getBoardNo");
		
		return cnt;
	}
	@Override
	public BoardVO eventSearch(SqlMapClient smc, int boardNo) throws SQLException {
		
		BoardVO boardVO = (BoardVO) smc.queryForObject("event.eventSearch", boardNo);
		
		return boardVO;
	}
	@Override
	public List<BoardVO> getEventList() throws SQLException {
		
		List<BoardVO> eventBoardList = new ArrayList<>();
		
		eventBoardList = smc.queryForList("event.getEventList");
		
		return eventBoardList;
	}
	

}
