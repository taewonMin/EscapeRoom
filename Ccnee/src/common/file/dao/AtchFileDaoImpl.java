package common.file.dao;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import common.util.SqlMapClientFactory;
import vo.BoardFileVO;

public class AtchFileDaoImpl implements AtchFileDao{
	
	private static AtchFileDao dao;
	private SqlMapClient smc;
	
	private AtchFileDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
	public static AtchFileDao getInstance() {
		if(dao == null) {
			dao = new AtchFileDaoImpl();
		}
		
		return dao;
	}
	
	@Override
	public int insertAtchFile(BoardFileVO boardFileVO) throws SQLException {
		
		int cnt = 0;
		
		Object obj = smc.insert("boardFile.insertEventFile", boardFileVO);
		
		if(obj == null) {
			cnt = 1;
		}
		
		return cnt;
	}

	@Override
	public List<BoardFileVO> getAtchFileList(int boardGrNo) throws SQLException {
		
		List<BoardFileVO> boardFileList = Collections.EMPTY_LIST;
		
		boardFileList = (List<BoardFileVO>)smc.queryForList("boardFile.getBoardFileList", boardGrNo);
		
		return boardFileList;
	}

	@Override
	public BoardFileVO getAtchFileDetail(BoardFileVO boardFileVO) throws SQLException {
		
		BoardFileVO boardFileDetail = (BoardFileVO)smc.queryForObject("boardFile.getAtchFileDetail", boardFileVO); 
		
		return boardFileDetail;
	}

	@Override
	public BoardFileVO eventFileSearch(int boardNo) throws SQLException{
		
		BoardFileVO boardFileVO = (BoardFileVO)smc.queryForObject("boardFile.eventFileSearch", boardNo);
		
		return boardFileVO;
	}

	@Override
	public int updateAtchFile(BoardFileVO boardFileVO) throws SQLException {
		
		int cnt = 0;
		
		Object obj = smc.insert("boardFile.updateEventFile", boardFileVO);
		
		if(obj == null) {
			cnt = 1;
		}
		
		return cnt;
	}

	@Override
	public int deleteFile(int boardNo) throws SQLException {
		
		int cnt = 0;
		
		cnt = smc.update("boardFile.deleteFile", boardNo);
		
		return cnt;
	}

//	@Override
//	public int insertDetailFile(BoardFileVO boardFileVO) throws SQLException{
//		
//		int cnt = 0;
//		
//		Object obj = smc.insert("boardFile.insertDetailFile", boardFileVO);
//		
//		if(obj == null) {
//			cnt = 1;
//		}
//		
//		return cnt;
//		
//	}

}
