package board.qa.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import common.util.BaseDao;
import vo.BoardVO;
import vo.PagingVO;
import vo.SearchVO;

public class QaDaoImpl extends BaseDao implements IQaDao {

	private SqlMapClient smc;
	private static IQaDao qaDao = null;
	
	private QaDaoImpl() {
		smc = getSqlMapClient();
	}
	
	public static IQaDao getInstance() {
		if(qaDao == null) {
			qaDao = new QaDaoImpl();
		}
		return qaDao;
	}
	
	@Override
	public void insertQa(BoardVO bv) throws SQLException {
		smc.insert("board.insertBoard", bv);
	}
	
	@Override
	public void updateQa(BoardVO bv) throws SQLException {
		smc.update("board.updateBoard", bv);
	}
	
	@Override
	public int deleteQa(int boardNo) throws SQLException {
		
		int cnt = 0;
		
		cnt = smc.delete("board.deleteBoard", boardNo);
		
		return cnt;
	}

	@Override
	public List<BoardVO> getAllBoard(SearchVO sv) throws SQLException {
		return smc.queryForList("board.getAllBoard", sv);
	}

	@Override
	public BoardVO getBoard(int boardNo) throws SQLException {
		BoardVO bv = (BoardVO) smc.queryForObject("board.getBoard", boardNo);
		return bv;
	}

	@Override
	public int updateCnt(int boardNo) throws SQLException {
		int cnt = 0;
		
		cnt = smc.update("board.updateCntBoard", boardNo);
		
		return cnt;
	}

	@Override
	public int getBoardAllCount(SearchVO sv) throws SQLException {
		return (int) smc.queryForObject("board.getBoardAllCount", sv);
	}

}
