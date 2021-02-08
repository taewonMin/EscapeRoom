package board.notice.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import common.util.BaseDao;
import vo.BoardVO;
import vo.PagingVO;
import vo.SearchVO;

public class NoticeDaoImpl extends BaseDao implements NoticeDao {
	private SqlMapClient smc;
	private static NoticeDao noticeDao=null;
	
	public NoticeDaoImpl() {
		smc = getSqlMapClient();
	};
	
	public static NoticeDao getInstance() {
		if(noticeDao == null) {
			noticeDao = new NoticeDaoImpl();
		}
		return noticeDao;
	}
	
	
	@Override
	public int insertNoticeBoard(BoardVO boardVO) throws SQLException {
		
		int cnt = 0;
		
		Object obj = smc.insert("board.insertBoard", boardVO);
		if(obj == null) {
			cnt = 1;
		}
		
		return cnt;
	};
	
	
	
	@Override
	public int updateNoticeBoard(BoardVO boardVO) throws SQLException {

		int cnt = 0;

		cnt = smc.update("board.updateBoard", boardVO);
		
		return cnt;
	}
	
	@Override
	public int deleteNoticeBoard(int boardNo) throws SQLException {
		
		int cnt = 0;
		
		cnt = smc.delete("board.deleteBoard", boardNo);
		
		return cnt;
	}
	
	
	
	@Override
	public List<BoardVO> getAllNoticeBoard(SearchVO sv) throws SQLException{
		
		List<BoardVO> noticeBoardList = smc.queryForList("board.getAllBoard",sv);
		
		return noticeBoardList;
		
	}
	
	@Override
	public BoardVO getSearchNoticeBoard(int boardNo) throws SQLException{
		
		BoardVO boardVO = (BoardVO) smc.queryForObject("board.getBoard", boardNo);
		
		return boardVO;
		
	}
	
	@Override
	public int getAllCountNoticeBoard(SearchVO sv) throws SQLException {
		
		int cnt = (int)smc.queryForObject("board.getBoardAllCount", sv);
		
		return cnt;
	}
	
	@Override
	public int updateCntBoard(int boardNo) throws SQLException {
		int cnt = 0;
		
		cnt = smc.update("board.updateCntBoard", boardNo);
		
		return cnt;
	}
	
	
}
